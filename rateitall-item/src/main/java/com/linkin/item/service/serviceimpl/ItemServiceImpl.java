package com.linkin.item.service.serviceimpl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linkin.common.dto.ItemDTO;
import com.linkin.common.entity.Item;
import com.linkin.item.mapper.ItemMapper;
import com.linkin.item.service.ItemService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ItemServiceImpl extends ServiceImpl<ItemMapper, Item> implements ItemService {
    @Autowired
    private ItemMapper itemMapper;

    public long uploadItem(ItemDTO itemDTO) {
        QueryWrapper<Item> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", itemDTO.getName());
        if (itemMapper.exists(queryWrapper)) {
            return -1L;
        } else {
            Item item = new Item();
            BeanUtils.copyProperties(itemDTO, item);
            item.setCreatedAt(LocalDateTime.now());
            itemMapper.insert(item);
            return item.getId();
        }
    }

    /**
     * 根据名称模糊查询相关物品
     * @param name
     * @return
     */

    public List<Item> getItemsByname(String name) {
        QueryWrapper<Item> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", name);
        return this.list(queryWrapper);
    }


}
