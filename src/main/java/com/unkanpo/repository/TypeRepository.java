package com.unkanpo.repository;

import com.unkanpo.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeRepository extends JpaRepository<Type,Long> {
    @Query(value = "SELECT t.name_type FROM Type as t JOIN GameTypes as gt ON t.id_type = gt.id_type WHERE gt.id_game = :value", nativeQuery = true)
    List<String> findTypes(@Param("value") Long idGame);
    Iterable<Type> findAllByNameType(String keyWord);
}
