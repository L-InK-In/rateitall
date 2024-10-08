package com.linkin.review.mapper;


import com.github.jeffreyning.mybatisplus.base.MppBaseMapper;
import com.linkin.review.pojo.entity.Review;
import com.linkin.review.pojo.vo.HotReviewVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ReviewMapper extends MppBaseMapper<Review> {

    List<HotReviewVo> selectHotReviews(@Param("itemIds")List<Long> itemIds);
}
