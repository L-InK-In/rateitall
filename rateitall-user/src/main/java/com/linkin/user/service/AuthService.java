package com.linkin.user.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.linkin.common.entity.User;
import com.linkin.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


public interface AuthService {

    public boolean login(String username, String password);

}
