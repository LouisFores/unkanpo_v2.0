package com.unkanpo.service.imp;

import com.unkanpo.model.Game;
import com.unkanpo.model.GameAccount;
import com.unkanpo.repository.AccountRepository;
import com.unkanpo.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService implements IAccountService {
    @Autowired
    private GameService gameService;
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
        GameAccount acc = account;
        acc.setIsRent(false);
        accountRepository.save(acc);
    }

    @Override
    public void delete(Long id) {
        accountRepository.deleteById(id);
    }

    @Override
    public Iterable<GameAccount> findAllByGame(Game game) {
        return accountRepository.findAllByGame(game);
    }
    public Iterable<GameAccount> findByIdGame(Long id) {
        Game game = gameService.findGameById(id).getGame();
        return accountRepository.findByGame(game);
    }

    public void rented(Long id) {
        GameAccount account = accountRepository.findById(id).get();
        account.setIsRent(true);
        accountRepository.save(account);
    }

    public void free(Long id) {
        GameAccount account  = accountRepository.findById(id).get();
        account.setIsRent(false);
        accountRepository.save(account);
    }
}
