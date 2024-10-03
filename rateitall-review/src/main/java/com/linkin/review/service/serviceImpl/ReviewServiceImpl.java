package com.linkin.review.service.serviceImpl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linkin.common.dto.ReviewDTO;
import com.linkin.common.entity.Review;
import com.linkin.review.mapper.ReviewMapper;
import com.linkin.review.service.ReviewService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ReviewServiceImpl extends ServiceImpl<ReviewMapper, Review> implements ReviewService {
    @Autowired
    private ReviewMapper reviewMapper;
    public long rateItem(ReviewDTO reviewDTO) {
        Review review = new Review();
        BeanUtils.copyProperties(reviewDTO, review);
        review.setCreatedAt(LocalDateTime.now());
        reviewMapper.insert(review);
        return review.getId();
    }
}
