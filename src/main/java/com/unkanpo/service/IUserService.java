package com.unkanpo.service;
import com.unkanpo.model.User;
import java.util.Optional;

public interface IUserService {
    Iterable<User> findAll();
    Optional<User> findById(Long id);
    Iterable<User> findAllByNamUser(String key_word);
    void save(User user);
    void deleteById(Long id);
    boolean  isNew(String email,String userName) throws Exception;
    boolean checkUser(String userName, String password);
    public Long Login(String userName, String password) throws Exception;
    public Boolean checkUserToken(String token);
    public User rechargeCoin(Long id,int amount) throws Exception;

}