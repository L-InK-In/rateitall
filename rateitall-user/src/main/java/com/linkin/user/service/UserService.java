package com.linkin.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.linkin.common.dto.UserDTO;
import com.linkin.common.entity.User;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;


import java.io.Serializable;


@Service
public interface UserService extends IService<User> {


    public long registerUser(UserDTO userDTO);

}
