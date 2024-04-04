package com.unkanpo.repository;

import com.unkanpo.model.Game;
import com.unkanpo.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
@Repository
public interface GameRepository extends CrudRepository<Game, Long> {

}