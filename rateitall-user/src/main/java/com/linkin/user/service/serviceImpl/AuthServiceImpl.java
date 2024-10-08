package com.linkin.user.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.linkin.user.mapper.UserMapper;
import com.linkin.user.pojo.entity.User;
import com.linkin.user.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public boolean login(String username, String password) {
        // 从数据库中查找用户
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("password");
        queryWrapper.eq("username", username);
        User user = userMapper.selectOne(queryWrapper);
        if (user != null) {
            // 进行密码验证
            return passwordEncoder.matches(password, user.getPassword());
        }
        return false;
    }
}
