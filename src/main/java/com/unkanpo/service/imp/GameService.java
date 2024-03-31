package com.unkanpo.service.imp;

import com.unkanpo.model.Game;
import com.unkanpo.model.Type;
import com.unkanpo.repository.GameRepository;
import com.unkanpo.service.IGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class GameService implements IGameService {
    @Autowired
    private GameRepository gameRepository;

    @Override
    public Iterable<Game> findAll() {
        return gameRepository.findAll();
    }

    @Override
    public Iterable<Game> findAllByNameGame(String keyword) {
        return null;
    }

    @Override
    public Optional<Game> findById(Long id) {
        return findById(id);
    }

    @Override
    public Set<Type> findGamesByGameId(Long id) {
        return gameRepository.findTypesByGameId(id);
    }

    @Override
    public void save(Game game) {
        gameRepository.save(game);
    }
}
