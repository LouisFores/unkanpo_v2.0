package com.unkanpo.repository;

import com.unkanpo.model.GameAccount;
import com.unkanpo.model.RentalHistory;
import com.unkanpo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RentalHistoryRepository extends JpaRepository<RentalHistory,Long> {
    @Query("SELECT rh FROM RentalHistory rh WHERE rh.isOnline = true")
    List<RentalHistory> findAllActiveRentalHistories();

    Optional<RentalHistory> findTopByUserAndGameAccountOrderByIdRentalDesc(User user, GameAccount gameAccount);
}
