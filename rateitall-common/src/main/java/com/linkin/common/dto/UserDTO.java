package com.linkin.common.dto;

import lombok.Data;
import java.io.Serializable;

@Data
public class UserDTO implements Serializable {

    /**
     * 新建用户
     */
    private String username;

    private String password;

    private String email;
}
