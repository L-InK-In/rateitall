package com.linkin.review.service;

import com.github.jeffreyning.mybatisplus.service.IMppService;
import com.linkin.review.pojo.entity.Review;
import com.linkin.review.pojo.vo.HotReviewVo;

import java.util.List;


public interface ReviewService extends IMppService<Review> {

    List<HotReviewVo> getHotReviews(List<Long> itemIds);

    HotReviewVo getHotReview(Long itemId);
}
