package com.unkanpo.service;

import com.unkanpo.model.Game;
import com.unkanpo.model.GameForm;
import com.unkanpo.service.imp.GameService;

import java.util.Optional;

public interface IGameService {
    Iterable<GameForm> findAll();
    Optional<Game> findById(Long id);
    void removeById(Long id);
    GameForm save(GameForm game);
}
