package com.unkanpo.service.imp;

import com.unkanpo.model.Type;
import com.unkanpo.repository.TypeRepository;
import com.unkanpo.service.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeService implements ITypeService {
    @Autowired
    private TypeRepository typeRepository;

    @Override
    public List<String> findTypes(Long idGame) {
        return typeRepository.findTypes(idGame);
    }
    @Override
    public List<Type> findAll() {
        return (List<Type>) typeRepository.findAll();
    }

    @Override
    public Iterable<Type> findAllByName_type(String key_word) {
        return typeRepository.findAllByNameTypeContaining(key_word);
    }

    public Type findAllByNameFirst(String key_word) {
        Iterable<Type> types =  typeRepository.findAllByNameTypeContaining(key_word);
        for (Type type : types) {
            return type;
        }
        return null;
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
    public void saveAll(List<Type> types) {
        typeRepository.saveAll(types);
    }

    @Override
    public Type findById(Long id) {
        return typeRepository.findById(id).get();
    }

}