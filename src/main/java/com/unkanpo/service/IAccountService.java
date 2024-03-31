package com.unkanpo.service;

import com.unkanpo.model.GameAccount;

public interface IAccountService {
    Iterable<GameAccount> findAll();
}
