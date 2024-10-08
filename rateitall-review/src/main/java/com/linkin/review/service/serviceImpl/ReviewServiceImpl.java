package com.linkin.review.service.serviceImpl;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import com.linkin.review.mapper.ReviewMapper;
import com.linkin.review.pojo.dto.ReviewDTO;
import com.linkin.review.pojo.entity.Review;
import com.linkin.review.pojo.vo.HotReviewVo;
import com.linkin.review.service.ReviewService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.Collections.max;

@Service
public class ReviewServiceImpl extends MppServiceImpl<ReviewMapper, Review> implements ReviewService {

    @Autowired
    private ReviewMapper reviewMapper;

    /**
     * 获取热评
     * @param itemIds
     * @return
     */
    @Override
    public List<HotReviewVo> getHotReviews(List<Long> itemIds) {
        QueryWrapper<Review> getMostLike = new QueryWrapper<>();
        List<HotReviewVo> hotReviewVos = reviewMapper.selectHotReviews(itemIds);
        return hotReviewVos;
    }

    @Override
    public HotReviewVo getHotReview(Long itemId) {
        HotReviewVo h = new HotReviewVo();
        QueryWrapper<Review> qw = new QueryWrapper<>();
        qw.eq("item_id", itemId);
        if(!reviewMapper.exists(qw)) {
            h.setContent("暂无评分");
            h.setUserId(-1L);
            h.setUpNum(-1L);
            return h;
        }
        qw.orderByDesc("up_num").last("limit 1");
        Review review = reviewMapper.selectOne(qw);
        BeanUtils.copyProperties(review, h);
        return h;
    }
}
