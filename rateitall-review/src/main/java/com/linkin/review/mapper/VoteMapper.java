package com.linkin.review.mapper;

import com.github.jeffreyning.mybatisplus.base.MppBaseMapper;

import com.linkin.review.pojo.entity.Vote;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VoteMapper extends MppBaseMapper<Vote> {
}
