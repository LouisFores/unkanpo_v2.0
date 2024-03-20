package com.unkanpo.repository;

import com.unkanpo.model.Game;
import org.springframework.data.repository.CrudRepository;

public interface GameRepository extends CrudRepository<Game,Long> {
}
