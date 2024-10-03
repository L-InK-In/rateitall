package com.linkin.common.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ReviewDTO implements Serializable {
    /**
     * 新建评价
     */
    private Long userId;
    private Long itemId;
    private String content;
    private int rating;
}
