package com.unkanpo.service.imp;

import com.unkanpo.model.Game;
import com.unkanpo.model.GameType;
import com.unkanpo.model.Type;
import com.unkanpo.repository.GameTypeRepository;
import com.unkanpo.service.IGameTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameTypeService implements IGameTypeService {
    @Autowired
    private GameTypeRepository gameTypeRepository;
    @Override
    public void saveAll(Game game, List<Type> types) {
        List<GameType> gameTypes = new ArrayList<>();
        for (Type type : types) {
            GameType gameType = new GameType(game,type );
            gameTypes.add(gameType);
        }
        gameTypeRepository.saveAll(gameTypes);
    }
}
