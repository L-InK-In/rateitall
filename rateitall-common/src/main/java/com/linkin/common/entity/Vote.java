package com.linkin.common.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@TableName(value = "votes")
@Getter
@Setter
public class Vote {

    @TableId(type = IdType.AUTO)
    private Long id;

    private Long userId;

    private Long reviewId;

    private Boolean isUpvote;  // true 代表点赞，false 代表踩
}
