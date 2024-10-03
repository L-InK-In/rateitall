package com.linkin.review.controller;


import com.linkin.common.dto.ReviewDTO;
import com.linkin.common.dto.VoteDTO;
import com.linkin.common.entity.Vote;
import com.linkin.review.openfeign.UserOpenFeign;
import com.linkin.review.service.ReviewService;
import com.linkin.review.service.VoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/reviews")
@Tag(name = "评论有关接口")
@Schema(name="review", description="评论相关功能")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private VoteService voteService;
    @Autowired
    private UserOpenFeign userOpenFeign;

    /**
     * 发表评论
     * @param reviewDTO
     * @return
     */
    @PostMapping("/rate")
    @Operation(summary = "发表评论")
    public ResponseEntity<?> rateItem(@RequestBody ReviewDTO reviewDTO) {
        reviewService.rateItem(reviewDTO);
        return ResponseEntity.ok("Already rated");
    }

    @PostMapping("/vote")
    @Operation(summary = "评论点赞/踩")
    public ResponseEntity<?> voteReview(@RequestBody VoteDTO voteDTO) {
        if (reviewService.getById(voteDTO.getReviewId()) != null &&
        userOpenFeign.UserIsExisted(voteDTO.getUserId())) {
            Vote vote = new Vote();
            BeanUtils.copyProperties(voteDTO, vote);
            vote.setUpdatedAt(LocalDateTime.now());
            voteService.saveOrUpdateByMultiId(vote);
            return ResponseEntity.ok("Already voted");
        }
        else {
            return ResponseEntity.status(403).body("Invalid request!");
        }
    }
}
