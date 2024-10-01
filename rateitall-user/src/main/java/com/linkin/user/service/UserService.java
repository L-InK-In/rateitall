package com.linkin.user.service;

import com.linkin.common.entity.User;
import com.linkin.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public User registerUser(String username, String password) {
        // 使用 BCrypt 将密码哈希化
        String hashedPassword = passwordEncoder.encode(password);
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(hashedPassword); // 存储哈希后的密码
        return userRepository.save(newUser);
    }
}
