package com.unkanpo.service.imp;

import com.unkanpo.model.*;
import com.unkanpo.repository.GameRepository;
import com.unkanpo.service.IGameService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

@Service
public class GameService implements IGameService {
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private GameTypeService gameTypeService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private GameImageService gameImageService;
    private String partUrl = "src\\main\\resources\\static\\image\\";

    private List<GameForm> getGameForms(List<Game> games) {
        List<GameForm> result = new ArrayList<>();
        for (int i = 0; i < games.size(); i++) {
            List<String> types = typeService.findTypes(games.get(i).getIdGame());
            GameForm game = new GameForm(games.get(i),types);
            result.add(game);
        }
        return result;
    }
    private GameForm getGameForm(Game game) {
        GameForm gameForm = new GameForm(game,typeService.findTypes(game.getIdGame()));
        return gameForm;
    }

    @Override
    public Iterable<GameForm> findAllByName_game(String key_word) {
        List<Game> games = (List<Game>) gameRepository.findAllByNameGameContaining(key_word);
        List<GameForm> result = getGameForms(games);
        return result;
    }

    @Override
    public GameForm save(GameForm gameForm) throws IOException {
        Game game = gameForm.getGame();
        if (gameForm.getBackground().getOriginalFilename() == "") {
            Game oldGame = new Game(game.getNameGame(), game.getDescriptionGame());
            oldGame.setIdGame(game.getIdGame());
            GameForm oldGameForm = new GameForm(game);
            oldGameForm.setTypes(gameForm.getTypes());
            saveGameType(oldGameForm, true);
        } else {
            game.setNameGame(game.getNameGame().trim());
            gameRepository.save(game);
            saveGameType(gameForm,true);
            saveIconGame(gameForm.getGame(),gameForm.getBackground());
        }

        return null;
    }

    @Override
    @Transactional
    public GameForm save(GameForm gameForm, List<MultipartFile> images) throws Exception{
        Game game = gameForm.getGame();
        String test = gameForm.getBackground().getOriginalFilename();
        try {
            game.setNameGame(game.getNameGame().trim());
            if (game.getIdGame() == null) {
                gameRepository.save(game);
                saveGameType(gameForm, false);
                saveImage(gameForm, images);
                saveIconGame(game,gameForm.getBackground());
            } else if (gameForm.getBackground().getOriginalFilename() == "") {
                Game oldGame = new Game(game.getNameGame(), game.getDescriptionGame());
                oldGame.setIdGame(game.getIdGame());
                GameForm oldGameForm = new GameForm(game);
                oldGameForm.setTypes(gameForm.getTypes());
                saveGameType(oldGameForm, true);
                saveImage(oldGameForm,images);
                gameImageService.save(new GameImage(game, game.getNameGame().trim().replace(" ","-")));
            }else {
                gameRepository.save(game);
                saveGameType(gameForm, true);
                saveImage(gameForm, images);
                saveIconGame(game,gameForm.getBackground());
            }
        }catch (Exception e) {
            throw new Exception(e);
        }
        return new GameForm(game);
    }
    public void saveImage(GameForm gameForm,List<MultipartFile> images) throws IOException {
        List<GameImage> gameImages = new ArrayList<>();
        int indexImage = 1;
        Game game = gameForm.getGame();
        String gameName = game.getNameGame().trim().replace(" ","-");
        for (MultipartFile image : images) {
            Files.copy(image.getInputStream(), Paths.get(partUrl + gameName +  "-" + indexImage + ".jpg"), StandardCopyOption.REPLACE_EXISTING);
            gameImages.add(new GameImage(game, gameName + "-" + indexImage));
            indexImage++;
        }
        gameImageService.saveAll(gameImages);
    }


    public void saveIconGame(Game game, MultipartFile background) throws IOException {
        String gameName = game.getNameGame().trim().replace(" ","-");
        Files.copy(background.getInputStream(), Paths.get(partUrl + gameName + ".jpg"), StandardCopyOption.REPLACE_EXISTING);
        gameImageService.save(new GameImage(game, gameName));
    }

    private void saveGameType(GameForm gameForm, boolean isExist) {
        List<Type> types = new ArrayList<>();
        if (isExist) {
            gameTypeService.deleteByGame(gameForm.getGame());
        }
        for (String id : gameForm.getTypes()) {
            types.add(typeService.findById(Long.parseLong(id)));
        }
        gameTypeService.saveAll(gameForm.getGame(),types);
    }

    @Override
    public List<GameForm> findAll() {
        List<Game> games = (List<Game>) gameRepository.findAll();
        List<GameForm> result = getGameForms(games);
        return result;
    }

    @Override
    public GameForm findGameById(Long id) {
        return getGameForm(gameRepository.findById(id).get());
    }

    @Override
    public void delete(Game game) {
        deleteImageFileByGame(game);
        gameTypeService.deleteByGame(game);
        gameImageService.deleteByGame(game);
        gameRepository.delete(game);
    }

    private void deleteImageFileByGame(Game game) {
        String gameName = game.getNameGame().trim().replace(" ","-");
        int indexImage = 1;
        File backgroundImage = new File(partUrl + gameName + ".jpg");
        if (backgroundImage.exists()) {
            backgroundImage.delete();
        }

        for (; indexImage <= gameImageService.getQuantityImageByGame(game); indexImage++) {
            File  imageFile = new File(partUrl + gameName + "-" + indexImage + ".jpg");
            if (imageFile.exists()) {
                imageFile.delete();
            }
        }
    }
    @Override
    public GameForm findById(Long id) {
        return   getGameForm(gameRepository.findById(id).get());
    }
    public boolean checkNameGameDuplicate(String name) {
        boolean isDuplicate = false;
        if (gameRepository.findByNameGame(name.trim()).isPresent()) {
            isDuplicate = true;
        }
        return isDuplicate;
    }
    public Game findByNameGame(String name) {
        return gameRepository.findByNameGame(name.trim()).get();
    }

    public boolean checkNameGameUpdate(Long id,String name) {
        boolean isChange = false;
        Game game = gameRepository.findById(id).get();
        if (!game.getNameGame().equals(name)) {
            isChange = true;
        }
        return isChange;
    }
}