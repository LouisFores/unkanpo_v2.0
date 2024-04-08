package com.unkanpo.service.imp;

import com.unkanpo.model.Game;
import com.unkanpo.model.GameForm;
import com.unkanpo.model.GameImage;
import com.unkanpo.model.Type;
import com.unkanpo.repository.GameRepository;
import com.unkanpo.service.IGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

//    private String partUrl = "C:\\CodeGym\\MyProject\\unkanpo_v2.0\\src\\main\\resources\\static\\image\\";
    private String partUrl = "F:\\Intellij Java\\unkanpo\\src\\main\\resources\\static\\image\\";

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
    public GameForm save(GameForm gameForm) {
        Game game = gameForm.getGame();
        if (game.getIdGame() == null) {
            gameRepository.save(game);
            saveGametype(gameForm, false);
        } else {
            gameRepository.save(game);
            saveGametype(gameForm,true);
        }
        return gameForm;
    }

    @Override
    public Iterable<GameForm> findAllByName_game(String key_word) {
        List<Game> games = (List<Game>) gameRepository.findAllByNameGameContaining(key_word);
        List<GameForm> result = getGameForms(games);
        return result;
    }

    public GameForm save(GameForm gameForm,List<MultipartFile> images) {
        Game game = gameForm.getGame();
        if (game.getIdGame() == null) {
            gameRepository.save(game);
            saveGametype(gameForm, false);
            saveImage(gameForm,images);
        } else {
            gameRepository.save(game);
            saveGametype(gameForm,true);
        }
        return gameForm;
    }
    public void saveImage(GameForm gameForm,List<MultipartFile> images){
        List<GameImage> gameImages = new ArrayList<>();
        int indexImage = 1;
        Game game = gameForm.getGame();
        String gameName = game.getNameGame().trim().replace(" ","-");
        try {
            saveIconGame(game,gameForm.getBackground());
            for (MultipartFile image : images) {
                FileCopyUtils.copy(image.getBytes(),new File(partUrl + gameName +  "-" + indexImage + ".jpg"));
                gameImages.add(new GameImage(game, gameName + "-" + indexImage));
                indexImage++;
            }
            gameImageService.saveAll(gameImages);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveIconGame(Game game, MultipartFile background) throws IOException {
        String gameName = game.getNameGame().trim().replace(" ","-");
        FileCopyUtils.copy(background.getBytes(), new File(partUrl + gameName + ".jpg"));
        gameImageService.save(new GameImage(game, gameName));
    }

    private void saveGametype(GameForm gameForm, boolean isExist) {
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
    public GameForm findById(Long id) {
        return   getGameForm(gameRepository.findById(id).get());
    }

    @Override
    public void delete(Game game) {
        gameTypeService.deleteByGame(game);
        gameRepository.delete(game);
    }
}