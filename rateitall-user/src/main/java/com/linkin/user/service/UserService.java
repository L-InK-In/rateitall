package com.linkin.user.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.linkin.user.pojo.dto.UserDTO;
import com.linkin.user.pojo.entity.User;
import org.springframework.stereotype.Service;



import java.io.Serializable;


@Service
public interface UserService extends IService<User> {


    public long registerUser(UserDTO userDTO);

}
