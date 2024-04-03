package com.unkanpo.service.imp;

import com.unkanpo.model.User;
import com.unkanpo.repository.UserRepository;
import com.unkanpo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean isNew(String email) {
       return !userRepository.findByEmail(email).get().getEmail().equals(email);
    }

    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Iterable<User> findAllByNamUser(String key_word) {
        return (Iterable<User>) userRepository.findByUsername(key_word);
    }

    @Override
    public void save(User user) {
        user.setRole("USER");
        userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public boolean checkUser(String userName, String password) {
        return userRepository.findByUsernameAndPassword(userName, password).isPresent();
    }

    public Long getId(String userName, String password) {
        return userRepository.findByUsernameAndPassword(userName, password).get().getIdUser();
    }
}
