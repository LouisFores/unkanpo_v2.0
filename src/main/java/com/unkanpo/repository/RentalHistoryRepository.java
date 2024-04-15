package com.unkanpo.repository;

import com.unkanpo.model.GameAccount;
import com.unkanpo.model.RentalHistory;
import com.unkanpo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RentalHistoryRepository extends JpaRepository<RentalHistory,Long> {
    Optional<RentalHistory> findTopByUserAndGameAccountOrderByIdRentalDesc(User user, GameAccount gameAccount);
}
