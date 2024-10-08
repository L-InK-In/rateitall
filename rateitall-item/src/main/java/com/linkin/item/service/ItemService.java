package com.linkin.item.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.linkin.item.pojo.entity.Item;
import com.linkin.item.pojo.vo.ItemVO;


import java.util.List;


public interface ItemService extends IService<Item> {


    /**
     * 根据名称查询物品列表
     * @param name
     * @return
     */
    List<Item> getItemsByname(String name);

    /**
     * 根据items获取相应vo
     * @param its
     * @return
     */
    List<ItemVO> listItemVO(List<Item> its);
}
