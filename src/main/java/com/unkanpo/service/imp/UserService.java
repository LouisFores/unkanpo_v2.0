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
    @Autowired
    private TokenService tokenService;

    @Override
    public boolean isNew(String email,String userName) throws Exception {
        boolean isNew = true;
        if (userRepository.findByEmail(email).isPresent()) {
            throw new Exception("Email already exists");
        } else if (userRepository.getUsernameByUsername(userName).isPresent()) {
            throw new Exception("UserName already exists");
        }
       return  true;
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

    @Override
    public Long Login(String userName, String password) throws Exception {
        Optional<User> user = userRepository.findByUsernameAndPassword(userName,password);

        if (!user.isPresent()) {
            throw new Exception("user not found");
        }
        return user.get().getIdUser();
    }

    @Override
    public Boolean checkUserToken(String token) {
        return null;
    }

    // maxCoin = 10 000 000 (ten million)
    @Override
    public User rechargeCoin(Long id, int coin) throws Exception {
        int maxCoin = 10000001;
        if (0 >= coin || coin > maxCoin) {
            throw new Exception("số tiền phải lớn hơn 0 và nhỏ hơn 10,000,000(mười triệu)");
        }

        User user = userRepository.findById(id).get();
        if (user.getCoin() + coin > maxCoin) {
            throw new Exception("số dư của bạn không thể vượt quá 10,000,000(mười triệu), bạn chỉ có thể nạp thêm: " + (maxCoin - coin));
        }

        user.setCoin(user.getCoin() + coin);
        userRepository.save(user);
        return user;
    }

    public boolean isNew(String email) {
        boolean isNew = userRepository.findByEmail(email).isPresent();
        return !isNew;
    }
    public Long getId(String userName, String password) {
        return userRepository.findByUsernameAndPassword(userName, password).get().getIdUser();
    }


}
