package com.linkin.review.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.linkin.common.dto.ReviewDTO;
import com.linkin.common.entity.Review;

public interface ReviewService extends IService<Review> {
    long rateItem(ReviewDTO reviewDTO);
}
