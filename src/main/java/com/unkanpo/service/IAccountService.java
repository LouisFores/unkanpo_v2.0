package com.unkanpo.service;

import com.unkanpo.model.GameAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public interface IAccountService {
    Iterable<GameAccount> findAll();
}
