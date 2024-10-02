package com.linkin.common.dto;

import com.linkin.common.entity.Tag;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ItemDTO implements Serializable {
    /**
     * 新建物品
     */
    private String name;

    private String description;

    private String picture;

    private Long creator;

    private List<String> tagList;
}
