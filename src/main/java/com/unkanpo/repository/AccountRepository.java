package com.unkanpo.repository;

import com.unkanpo.model.Game;
import com.unkanpo.model.GameAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<GameAccount,Long> {
    Optional<GameAccount> findByHideInfoAndGame(String username_password, Game game);
}

