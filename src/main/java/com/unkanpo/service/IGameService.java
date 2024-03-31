package com.unkanpo.service;

import com.unkanpo.model.Game;
import com.unkanpo.model.Type;

import java.util.Optional;
import java.util.Set;

public interface IGameService {
    Iterable<Game> findAll();
    Iterable<Game> findAllByNameGame(String keyword);
    Optional<Game> findById(Long id);
    Set<Type> findGamesByGameId(Long id);
    void save(Game type);
}
