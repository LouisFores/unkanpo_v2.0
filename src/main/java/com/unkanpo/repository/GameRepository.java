package com.unkanpo.repository;

import com.unkanpo.model.Game;
import com.unkanpo.model.Type;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.Set;

public interface GameRepository extends CrudRepository<Game,Long> {
    @Query("SELECT g.types FROM Game g WHERE g.idGame = :idGame")
    Set<Type> findTypesByGameId(@Param("idGame") Long id);
}
