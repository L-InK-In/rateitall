package com.linkin.item.service.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linkin.common.entity.Item;
import com.linkin.common.entity.ItemTag;
import com.linkin.common.entity.Tag;
import com.linkin.item.mapper.ItemTagMapper;
import com.linkin.item.service.ItemTagService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemTagServiceImpl extends ServiceImpl<ItemTagMapper, ItemTag> implements ItemTagService {

    @Autowired
    private ItemTagMapper itemTagMapper;

    public List<Long> getItemsByTag(Long tagId) {
        QueryWrapper<ItemTag> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("item_id").eq("tag_id", tagId);
        return itemTagMapper.selectObjs(queryWrapper);
    }

    public List<Long> getTagsOfItem(Long itemId) {
        QueryWrapper<ItemTag> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("tag_id").eq("item_id", itemId);
        return itemTagMapper.selectObjs(queryWrapper);
    }
}
