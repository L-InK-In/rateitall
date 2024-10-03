package com.linkin.review.service.serviceImpl;


import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import com.linkin.common.entity.Vote;
import com.linkin.review.mapper.VoteMapper;
import com.linkin.review.service.VoteService;
import org.springframework.stereotype.Service;

@Service
public class VoteServiceImpl extends MppServiceImpl<VoteMapper, Vote> implements VoteService {
}
