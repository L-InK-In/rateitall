package com.linkin.common.entity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.jeffreyning.mybatisplus.anno.MppMultiId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@TableName(value = "review_votes")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@Schema(name = "Vote", description = "赞/踩实体类")
public class Vote {

    @MppMultiId
    @Schema(name = "userId", description = "点赞/踩者id")
    private Long userId;

    @MppMultiId
    @Schema(name = "reviewId", description = "评论id")
    private Long reviewId;

    @Schema(name = "sUpvote", description = "赞/踩")
    private Boolean isUpvote;  // true 代表点赞，false 代表踩

    @Schema(name = "updateAt", description = "点赞/踩时间")
    private LocalDateTime updatedAt;
}
