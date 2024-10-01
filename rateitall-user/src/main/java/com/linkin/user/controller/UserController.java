package com.linkin.user.controller;

import com.linkin.common.entity.User;
import com.linkin.user.service.AuthService;
import com.linkin.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginUser) {
        // 调用服务层进行登录验证
        if (authService.login(loginUser.getUsername(), loginUser.getPassword())) {
            return ResponseEntity.ok("ok");
        }
        // 验证失败，返回错误信息
        return ResponseEntity.status(401).body("Invalid username or password");
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User newUser) {
        userService.registerUser(newUser.getUsername(), newUser.getPassword());
        return ResponseEntity.ok("User registered successfully");
    }


}
