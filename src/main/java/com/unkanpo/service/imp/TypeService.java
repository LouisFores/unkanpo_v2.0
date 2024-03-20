package com.unkanpo.service.imp;

import com.unkanpo.model.Type;
import com.unkanpo.repository.TypeRepository;
import com.unkanpo.service.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TypeService implements ITypeService {

    @Autowired
    private TypeRepository typeRepository;
    @Override
    public Iterable<Type> findAll() {
        return typeRepository.findAll();
    }

    @Override
    public Iterable<Type> findAllByName_type(String key_word) {
        return typeRepository.findAllByNameType(key_word);
    }

    @Override
    public void save(Type type) {
        typeRepository.save(type);
    }

    @Override
    public void deleteById(Long id) {
        typeRepository.deleteById(id);
    }

    @Override
    public Optional<Type> findById(Long id) {
        return typeRepository.findById(id);
    }
}