package com.linkin.user.service;

import com.linkin.common.entity.User;
import com.linkin.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public boolean login(String username, String password) {
        // 从数据库中查找用户
        User user = userRepository.findByUsername(username);
        if (user != null) {
            // 进行密码验证（这里只是简单示例，实际项目中应使用加密）
            return user.getPassword().equals(passwordEncoder.encode(password));
        }
        return false;
    }

}
