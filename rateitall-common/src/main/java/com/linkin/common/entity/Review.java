package com.linkin.common.entity;



import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


import java.util.Date;


@TableName(value = "reviews")
@Data
public class Review {

    @TableId(type = IdType.AUTO)
    private Long id;

    private Long userId;

    private Long itemId;

    private String content;

    private int rating;

    private Date createdAt;

    private Date updatedAt;
}
