package com.linkin.common.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@TableName(value = "tags")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@Schema(name = "Tag", description = "标签实体类")
public class Tag {

    @TableId(type = IdType.AUTO)
    @Schema(name = "id", description = "标签id")
    private Long id;

    @Schema(name = "name", description = "物品名")
    private String name;
}
