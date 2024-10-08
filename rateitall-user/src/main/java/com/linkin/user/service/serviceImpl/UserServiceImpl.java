package com.linkin.user.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.linkin.user.mapper.UserMapper;
import com.linkin.user.pojo.dto.UserDTO;
import com.linkin.user.pojo.entity.User;
import com.linkin.user.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public long registerUser(UserDTO userDTO) {
        // 使用 BCrypt 将密码哈希化
        String hashedPassword = passwordEncoder.encode(userDTO.getPassword());
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        user.setPassword(hashedPassword); // 存储哈希后的密码
        return userMapper.insert(user);
    }

}
