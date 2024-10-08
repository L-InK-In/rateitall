package com.linkin.item.service.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.linkin.item.mapper.ItemMapper;
import com.linkin.item.openfeign.ReviewOpenFeign;
import com.linkin.item.pojo.entity.Item;
import com.linkin.item.pojo.vo.ItemVO;
import com.linkin.item.service.ItemService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemServiceImpl extends ServiceImpl<ItemMapper, Item> implements ItemService {


    @Qualifier("com.linkin.item.openfeign.ReviewOpenFeign")
    @Autowired
    private ReviewOpenFeign reviewOpenFeign;

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

    /**
     * 根据评分对象求出对应vo对象
     * @param its
     * @return
     */
    @Override
    public List<ItemVO> listItemVO(List<Item> its) {
        List<ItemVO> itemVOS = new ArrayList<>();
        for (Item item : its) {
            ItemVO itemVO = new ItemVO();
            BeanUtils.copyProperties(item, itemVO);
            itemVO.setAvgScore(reviewOpenFeign.getItemAvgScore(item.getId()));
            itemVO.setReviewNum(reviewOpenFeign.getItemReviews(item.getId()));
            itemVO.setDescription(reviewOpenFeign.getHotReviewContent(item.getId()));
            itemVOS.add(itemVO);
        }
        return itemVOS;
    }
}
