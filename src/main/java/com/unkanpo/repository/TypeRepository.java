package com.unkanpo.repository;

import com.unkanpo.model.Type;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface TypeRepository extends CrudRepository<Type,Long> {
//    Iterable<Type> findAllByName_type(String keyWord);
    Iterable<Type> findAllByNameType(String keyWord);
}
