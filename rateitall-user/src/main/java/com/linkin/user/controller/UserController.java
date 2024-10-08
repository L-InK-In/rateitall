package com.linkin.user.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.linkin.user.pojo.dto.UserDTO;
import com.linkin.user.pojo.dto.UserLoginDTO;
import com.linkin.user.pojo.entity.User;
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
    public ResponseEntity<?> registerUser(@RequestBody UserDTO newUser) {
        QueryWrapper<User> duplicateCheck = new QueryWrapper<>();
        duplicateCheck.eq("name", newUser.getUsername());
        if (userService.getOneOpt(duplicateCheck).isPresent()) {
            return ResponseEntity.status(403).body("Duplicate name!");
        }
        duplicateCheck.clear();
        duplicateCheck.eq("email", newUser.getEmail());
        if (userService.getOneOpt(duplicateCheck).isPresent()) {
            return ResponseEntity.status(403).body("Duplicate Email!");
        }
        userService.registerUser(newUser);
        return ResponseEntity.ok("User registered successfully");
    }

    @RequestMapping(value = "/check/{id}", method=RequestMethod.GET)
    @Operation(summary = "检查用户是否存在")
    public boolean UserIsExisted(@PathVariable("id") long id) {
        return userService.getById(id) != null;
    }
    @RequestMapping(value = "/getName/{id}", method=RequestMethod.GET)
    @Operation(summary = "获取用户名字")
    public String getNameById(@PathVariable("id") long id) {
        return userService.getById(id).getUsername();
    }

}
