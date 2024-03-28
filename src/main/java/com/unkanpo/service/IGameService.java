package com.unkanpo.service;

import com.unkanpo.model.Game;
import com.unkanpo.model.GameForm;
import com.unkanpo.service.imp.GameService;

import java.util.Optional;

public interface IGameService {
    Iterable<GameForm> findAll();
    GameForm findById(Long id);
    void delete(Game game);
    GameForm save(GameForm game);
}
