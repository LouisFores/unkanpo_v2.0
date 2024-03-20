package com.unkanpo.service;

import com.unkanpo.model.Type;

import java.util.Optional;

public interface ITypeService {

    Iterable<Type> findAll();
    Iterable<Type> findAllByName_type(String key_word);
    Optional<Type> findById(Long id);
    void save(Type type);
    void deleteById(Long id);


}
