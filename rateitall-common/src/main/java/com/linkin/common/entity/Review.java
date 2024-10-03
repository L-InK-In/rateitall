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



@TableName(value = "reviews")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@Schema(name = "Review", description = "评价实体类")
public class Review {

    @TableId(type = IdType.AUTO)
    @Schema(name = "id", description = "评价id")
    private Long id;

    @Schema(name = "userId", description = "评价者id")
    private Long userId;

    @Schema(name = "itemId", description = "被评价物品id")
    private Long itemId;

    @Schema(name = "content", description = "评价内容")
    private String content;

    @Schema(name = "rating", description = "评价分数")
    private int rating;

    @Schema(name = "createdAt", description = "评价创建时间")
    private LocalDateTime createdAt;

    @Schema(name = "updateAt", description = "评价修改时间")
    private LocalDateTime updatedAt;
}
