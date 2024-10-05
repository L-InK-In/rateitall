package com.linkin.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@TableName(value = "item_tags")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@Schema(name = "Item_tag", description = "物品与标签关系类")
public class ItemTag {
    @TableId(type = IdType.AUTO)
    @Schema(name = "id", description = "物品与标签关系的id")
    private Long id;

    @Schema(name = "itemId", description = "物品id")
    private Long itemId;
    @Schema(name = "tagId", description = "标签id")
    private Long tagId;
}
