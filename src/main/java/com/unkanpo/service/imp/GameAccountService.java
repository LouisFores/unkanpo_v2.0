package com.unkanpo.service.imp;

import com.unkanpo.model.Game;
import com.unkanpo.model.GameAccount;
import com.unkanpo.repository.GameAccountRepository;
import com.unkanpo.service.IGameAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameAccountService implements IGameAccountService {
    @Autowired
    private GameAccountRepository gameAccountRepository;
    @Override
    public Iterable<GameAccount> findAll() {
        return gameAccountRepository.findAll();
    }

    @Override
    public void save(GameAccount gameAccount) {
        gameAccountRepository.save(gameAccount);
    }

    @Override
    public void delete(GameAccount gameAccount) {
        gameAccountRepository.delete(gameAccount);
    }

    @Override
    public Iterable<GameAccount> findAllByGame(Game game) {
        return gameAccountRepository.findAllByGame(game);
    }
}
