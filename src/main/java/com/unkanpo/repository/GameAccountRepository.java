package com.unkanpo.repository;

import com.unkanpo.model.Game;
import com.unkanpo.model.GameAccount;
import org.springframework.data.repository.CrudRepository;

public interface GameAccountRepository extends CrudRepository<GameAccount, Long> {
    Iterable<GameAccount> findAllByGame(Game game);

}
