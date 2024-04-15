package com.unkanpo.service;

import com.unkanpo.model.Type;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ITypeService {
    List<String> findTypes(Long idGame);
    Iterable<Type> findAll();

    Iterable<Type> findAllByName_type(String key_word);
    Type findById(Long id);
    void save(Type type);
    void saveAll(List<Type> types);
    void deleteById(Long id);

}
