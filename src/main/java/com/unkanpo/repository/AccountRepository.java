package com.unkanpo.repository;

import com.unkanpo.model.GameAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<GameAccount,Long> {
}
