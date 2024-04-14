package com.unkanpo.repository;

import com.unkanpo.model.Game;
import com.unkanpo.model.GameImage;
import org.springframework.data.jpa.repository.JpaRepository;
<<<<<<< HEAD
import org.springframework.stereotype.Repository;
=======
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;
>>>>>>> 321d96e390289ddd3320825fa543bf6742d5ab94

@Repository
public interface GameImageRepository extends JpaRepository<GameImage,Long> {
    int countByGame(Game game);

    void deleteByGame(Game game);
}
