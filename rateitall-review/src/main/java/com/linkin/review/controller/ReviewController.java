package com.linkin.review.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;

import com.linkin.review.openfeign.ItemOpenFeign;
import com.linkin.review.openfeign.UserOpenFeign;
import com.linkin.review.pojo.dto.ReviewDTO;
import com.linkin.review.pojo.dto.VoteDTO;
import com.linkin.review.pojo.entity.Review;
import com.linkin.review.pojo.entity.Vote;
import com.linkin.review.pojo.vo.HotReviewVo;
import com.linkin.review.service.ReviewService;
import com.linkin.review.service.VoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Map;

@RestController
@RequestMapping("/reviews")
@Tag(name = "评论有关接口")
@Schema(name="review", description="评论相关功能")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private VoteService voteService;
    @Qualifier("com.linkin.review.openfeign.UserOpenFeign")
    @Autowired
    private UserOpenFeign userOpenFeign;
    @Qualifier("com.linkin.review.openfeign.ItemOpenFeign")
    @Autowired
    private ItemOpenFeign itemOpenFeign;

    /**
     * 发表评论
     * @param reviewDTO
     * @return
     */
    @PostMapping("/rate")
    @Operation(summary = "发表评论")
    public ResponseEntity<?> rateItem(@RequestBody ReviewDTO reviewDTO) {
        if (!itemOpenFeign.ItemIsExisted(reviewDTO.getItemId()) ||
        !userOpenFeign.UserIsExisted(reviewDTO.getUserId())) {
            return ResponseEntity.status(403).body("Invalid request!");
        }
        Review review = new Review();
        BeanUtils.copyProperties(reviewDTO, review);
        reviewService.saveOrUpdateByMultiId(review);
        return ResponseEntity.ok("Already rated");
    }

    /**
     * 评论点赞/踩
     * @param voteDTO
     * @return
     */
    @PostMapping("/vote")
    @Operation(summary = "评论点赞/踩")
    @Transactional
    public ResponseEntity<?> voteReview(@RequestBody VoteDTO voteDTO) {
        // check the reviewId
        if (reviewService.getOptById(voteDTO.getReviewId()).isEmpty()) {
            return ResponseEntity.status(403).body("Invalid reviewId!");
        }
        // check the userId
        if (!userOpenFeign.UserIsExisted(voteDTO.getUserId())) {
            return ResponseEntity.status(403).body("Invalid userId!");
        }
        //
        Vote vote = new Vote();
        BeanUtils.copyProperties(voteDTO, vote);
        Vote existedVote = voteService.selectByMultiId(vote);
        // decrease the up-vote num
        if (existedVote != null && existedVote.getIsUpvote()) {
            UpdateWrapper<Review> decreaseUpdate = new UpdateWrapper<>();
            decreaseUpdate.setSql("up_num = up_num - 1");
            decreaseUpdate.eq("id", voteDTO.getReviewId());
            reviewService.update(decreaseUpdate);
        }
        // same vote meaning delete
        if (existedVote != null && existedVote.getIsUpvote().equals(voteDTO.getIsUpvote())) {
            voteService.deleteByMultiId(vote);
            return ResponseEntity.ok("Cancel vote");
        }
        voteService.saveOrUpdateByMultiId(vote);
        // if like then update the review table
        if (voteDTO.getIsUpvote()) {
            UpdateWrapper<Review> increaseUpdate = new UpdateWrapper<>();
            increaseUpdate.setSql("up_num = up_num + 1");
            increaseUpdate.eq("id", voteDTO.getReviewId());
            reviewService.update(increaseUpdate);
        }
        return ResponseEntity.ok("Already voted");
    }

    /**
     * 获取点赞数最多的评论
     * @param itemId
     * @return
     */
    @GetMapping("/getHotReview/{itemId}")
    @Operation(summary = "获取热评")
    public ResponseEntity<?> getHotReviewByid(@PathVariable("itemId") Long itemId) {
        HotReviewVo h = reviewService.getHotReview(itemId);
        return ResponseEntity.ok().body(h);
    }

    /**
     * 获取点赞数最多的评论正文
     * @param itemId
     * @return
     */
    @RequestMapping(value ="/getHotReviewContent/{itemId}", method=RequestMethod.GET)
    public String getHotReviewContent(@PathVariable("itemId") Long itemId) {
        return reviewService.getHotReview(itemId).getContent();
    }

    /**
     * 获取评分对象的平均分
     * @param itemId
     * @return
     */
    @RequestMapping(value ="/getAvgScore/{itemId}", method=RequestMethod.GET)
    @Operation(summary = "获取平均分")
    public String getItemAvgScore(@PathVariable("itemId") Long itemId) {
        QueryWrapper<Review> getAvg = new QueryWrapper<>();
        getAvg.eq("item_id", itemId).select("AVG(rating) as s");
        Map<String, Object> avgRes = reviewService.getMap(getAvg);
        if (avgRes == null) {
            return "No reviews";
        }
        BigDecimal avgScore = (BigDecimal) avgRes.get("s");
        DecimalFormat df = new DecimalFormat("#.0");//此为保留1位小数，
        return df.format(avgScore);
    }

    /**
     * 获取评分对象的评分人数
     * @param itemId
     * @return
     */
    @RequestMapping(value ="/getReviewNum/{itemId}", method=RequestMethod.GET)
    @Operation(summary = "获取评分人数")
    public Long getItemReviews(@PathVariable("itemId")  Long itemId) {
        QueryWrapper<Review> sameItemId = new QueryWrapper<>();
        sameItemId.eq("item_id", itemId);
        return reviewService.count(sameItemId);
    }

}
