package com.unkanpo.service;

import com.unkanpo.model.GameAccount;

import java.util.Optional;


public interface IAccountService {
    Iterable<GameAccount> findAll();
    boolean IsExist(GameAccount account);
    void save(GameAccount account);
    Optional<GameAccount> findById(Long id);
    void delete(Long id);
    Iterable<GameAccount> findByIdGame(Long id);
}
