package com.linkin.user.controller;

import com.linkin.common.dto.UserDTO;
import com.linkin.common.dto.UserLoginDTO;
import com.linkin.user.service.AuthService;
import com.linkin.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
@Tag(name = "用户有关接口")
@Schema(name="users", description="用户注册与登录")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    /**
     * 用户登录
     * @param loginUser
     * @return
     */
    @PostMapping("/login")
    @Operation(summary = "登录")
    @Parameter(ref = "用户信息")
    public ResponseEntity<?> login(@RequestBody UserLoginDTO loginUser) {
        // 调用服务层进行登录验证
        if (authService.login(loginUser.getUsername(), loginUser.getPassword())) {
            return ResponseEntity.ok("ok");
        }
        // 验证失败，返回错误信息
        return ResponseEntity.status(401).body("Invalid username or password");
    }

    /**
     * 用户注册
     * @param newUser
     * @return
     */
    @PostMapping("/register")
    @Operation(summary = "注册")
    @Parameter(ref = "用户信息")
    public ResponseEntity<?> registerUser(@RequestBody UserDTO newUser) {
        userService.registerUser(newUser);
        return ResponseEntity.ok("User registered successfully");
    }

    @RequestMapping(value = "/check/{id}", method=RequestMethod.GET)
    @Operation(summary = "检查用户是否存在")
    @Parameter(ref = "用户id")
    public boolean UserIsExisted(@PathVariable("id") long id) {
        return userService.getById(id) != null;
    }

}
