package com.unkanpo.service.imp;

import com.unkanpo.model.Game;
import com.unkanpo.model.GameForm;
import com.unkanpo.model.Type;
import com.unkanpo.repository.GameRepository;
import com.unkanpo.service.IGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
