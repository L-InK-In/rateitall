package com.linkin.user.service;

import com.linkin.common.dto.UserDTO;
import com.linkin.common.entity.User;
import com.linkin.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public long registerUser(UserDTO userDTO) {
        // 使用 BCrypt 将密码哈希化
        String hashedPassword = passwordEncoder.encode(userDTO.getPassword());
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(hashedPassword); // 存储哈希后的密码
        user.setEmail(userDTO.getEmail());
        user.setCreatedAt(LocalDateTime.now());
        return userMapper.insert(user);
    }
}
