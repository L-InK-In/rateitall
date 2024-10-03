package com.linkin.item.service.serviceimpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linkin.common.entity.Tag;
import com.linkin.item.mapper.TagMapper;
import com.linkin.item.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    @Autowired
    private TagMapper tagMapper;

    public void saveBatchByName(List<String> tagList) {
        tagMapper.insertBatchByName(tagList);
    }
}
