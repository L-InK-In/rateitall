package com.linkin.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@TableName(value = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@Schema(name = "User", description = "用户实体类")
public class User {

    @TableId(type = IdType.AUTO)
    @Schema(name = "id", description = "用户id")
    private Long id;

    @Schema(name = "username", description = "用户名")
    private String username;

    @Schema(name = "password", description = "密码")
    private String password;

    @Schema(name = "email", description = "邮箱")
    private String email;

    @Schema(name = "avatar", description = "头像")
    private String avatar;

    @Schema(name = "createdAt", description = "创建时间")
    private LocalDateTime createdAt;

}
