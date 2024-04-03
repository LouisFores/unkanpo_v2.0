package com.unkanpo.service;
import com.unkanpo.model.Type;
import com.unkanpo.model.User;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

//public interface IUserService extends UserDetailsService {
//    void save(User user);
//
//    Iterable<User> findAll();
//
//    User findByUsername(String username);
//
//    User getCurrentUser();
//
//    Optional<User> findById(Long id);
//
//    UserDetails loadUserById(Long id);
//    public Page<User> getAllUsers(Pageable pageable);
//    boolean checkLogin(User user);
//
//    boolean isRegister(User user);
//
//    boolean isCorrectConfirmPassword(User user);
//}

public interface IUserService {
    Iterable<User> findAll();
    Optional<User> findById(Long id);
    Iterable<User> findAllByNamUser(String key_word);
    void save(User user);
    void deleteById(Long id);
    boolean  isNew(String email);
    boolean checkUser(String userName, String password);
}