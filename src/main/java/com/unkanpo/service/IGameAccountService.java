package com.unkanpo.service;

import com.unkanpo.model.Game;
import com.unkanpo.model.GameAccount;
import com.unkanpo.model.Type;

import java.util.Optional;
import java.util.Set;

public interface IGameAccountService {
    Iterable<GameAccount> findAll();
    void save(GameAccount gameAccount);
    void delete(GameAccount gameAccount);
    Iterable<GameAccount> findAllByGame(Game game);

}
