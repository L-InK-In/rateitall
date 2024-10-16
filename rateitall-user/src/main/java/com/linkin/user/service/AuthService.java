package com.linkin.user.service;

import com.linkin.user.pojo.entity.User;

public interface AuthService {

    public User login(String username, String password);

}
