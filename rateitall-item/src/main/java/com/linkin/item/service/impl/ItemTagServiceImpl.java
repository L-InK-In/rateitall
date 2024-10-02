package com.linkin.item.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linkin.common.entity.ItemTag;
import com.linkin.item.mapper.ItemTagMapper;
import com.linkin.item.service.ItemTagService;
import org.springframework.stereotype.Service;

@Service
public class ItemTagServiceImpl extends ServiceImpl<ItemTagMapper, ItemTag> implements ItemTagService {
}
