package com.unkanpo.repository;

import com.unkanpo.model.Game;
import com.unkanpo.model.GameType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GameTypeRepository extends JpaRepository<GameType,Long> {
        @Modifying
        @Query(value = "DELETE FROM GameType gt WHERE gt.game = :value")
        void deleteByGameId(@Param("value") Long gameId);

        void deleteByGame(Game game);
}
