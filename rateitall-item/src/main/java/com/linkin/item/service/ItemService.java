package com.linkin.item.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.linkin.common.dto.ItemDTO;
import com.linkin.common.entity.Item;
import com.linkin.item.mapper.ItemMapper;
import com.linkin.item.mapper.TagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ItemService extends IService<Item> {

    /**
     * 上传物品
     * @param itemDTO
     * @return
     */
    public long uploadItem(ItemDTO itemDTO);


    /**
     * 根据名称查询物品列表
     * @param name
     * @return
     */
    List<Item> getItemsByname(String name);
}
