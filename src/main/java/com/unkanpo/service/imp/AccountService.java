package com.unkanpo.service.imp;

import com.unkanpo.model.GameAccount;
import com.unkanpo.repository.AccountRepository;
import com.unkanpo.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService implements IAccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Iterable<GameAccount> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Optional<GameAccount> findById(Long id) {
        return accountRepository.findById(id);
    }

    @Override
    public boolean IsExist(GameAccount account) {
        boolean isExist = false;
        if (accountRepository.findByHideInfoAndGame(account.getHideInfo(), account.getGame()).isPresent()) {
            isExist = true;
        }
        return isExist;
    }

    @Override
    public void save(GameAccount account) {
        accountRepository.save(account);
    }

    @Override
    public void delete(Long id) {
        accountRepository.deleteById(id);
    }
}
