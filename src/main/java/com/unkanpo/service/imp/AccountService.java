package com.unkanpo.service.imp;

import com.unkanpo.model.GameAccount;
import com.unkanpo.repository.AccountRepository;
import com.unkanpo.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements IAccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Iterable<GameAccount> findAll() {
        return accountRepository.findAll();
    }
}
