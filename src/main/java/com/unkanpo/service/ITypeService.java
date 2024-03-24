package com.unkanpo.service;

import com.unkanpo.model.Type;

import java.util.Optional;

public interface ITypeService {

    Iterable<Type> findAll();
    Iterable<Type> findAllByNameType(String keyword);
    Optional<Type> findById(Long id);
    void save(Type type);

}
