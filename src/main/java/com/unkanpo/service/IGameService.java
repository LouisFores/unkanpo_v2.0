package com.unkanpo.service;

import com.unkanpo.model.Game;
import com.unkanpo.model.GameForm;

public interface IGameService {
    Iterable<GameForm> findAll();
    GameForm findGameById(Long id);
    void delete(Game game);
    GameForm save(GameForm game);
}