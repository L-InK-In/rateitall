package com.linkin.common.dto;

import lombok.Data;

import java.io.Serializable;
@Data
public class UserLoginDTO implements Serializable {
    /**
     * 用户登录
     */
    private String username;

    private String password;

    private String email;
}
