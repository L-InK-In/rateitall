package com.linkin.item.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.linkin.common.entity.Item;
import com.linkin.common.entity.ItemTag;
import com.linkin.common.entity.Tag;

import java.util.List;

public interface ItemTagService extends IService<ItemTag> {

    /**
     * 查询所有带有某标签的物品
     * @param tagId
     * @return
     */
    List<Long> getItemsByTag(Long tagId);

    /**
     * 查询物品所有标签
     * @param itemId
     * @return
     */
    List<Long> getTagsOfItem(Long itemId);

}
