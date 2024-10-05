package com.linkin.item.service.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linkin.common.entity.ItemTag;
import com.linkin.common.entity.Tag;
import com.linkin.item.mapper.TagMapper;
import com.linkin.item.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    @Autowired
    private TagMapper tagMapper;

    public void saveBatchByName(List<String> tagList) {
        tagMapper.insertBatchByName(tagList);
    }

    @Override
    public Long getIdByName(String name) {
        QueryWrapper<Tag> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id").eq("name", name);
        return tagMapper.selectOne(queryWrapper).getId();
    }

    @Override
    public List<String> getNamesByIds(List<Long> ids) {
        QueryWrapper<Tag> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("name").in("id", ids);
        return tagMapper.selectList(queryWrapper).stream().map(Tag::getName).collect(Collectors.toList());
    }
}
