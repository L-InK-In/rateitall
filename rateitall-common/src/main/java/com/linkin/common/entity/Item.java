package com.linkin.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@TableName(value = "items")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@Schema(name = "Item", description = "物品实体类")
public class Item {

    @TableId(type = IdType.AUTO)
    @Schema(name = "id", description = "物品id")
    private Long id;

    @Schema(name = "name", description = "物品名")
    private String name;

    @Schema(name = "description", description = "物品简介")
    private String description;

    @Schema(name = "picture", description = "物品照片")
    private String picture;

    @Schema(name = "createdAt", description = "创建时间")
    private LocalDateTime createdAt;

    @Schema(name = "updatedAt", description = "更新时间")
    private LocalDateTime updatedAt;

    @Schema(name = "creator", description = "创建者")
    private Long creator;
}
