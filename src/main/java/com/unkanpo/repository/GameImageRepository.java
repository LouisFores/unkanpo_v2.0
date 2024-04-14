package com.unkanpo.repository;

import com.unkanpo.model.Game;
import com.unkanpo.model.GameImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GameImageRepository extends JpaRepository<GameImage,Long> {
    int countByGame(Game game);

    void deleteByGame(Game game);
}
