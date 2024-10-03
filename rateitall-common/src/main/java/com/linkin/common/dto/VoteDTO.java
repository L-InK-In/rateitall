package com.linkin.common.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
@Data
public class VoteDTO implements Serializable {

    /**
     * 点赞/踩属性
     */
    private Long userId;
    private Long reviewId;
    private Boolean isUpvote;  // true 代表点赞，false 代表踩
}
