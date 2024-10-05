package com.linkin.item.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.linkin.common.dto.ItemDTO;
import com.linkin.common.entity.Item;
import com.linkin.common.entity.ItemTag;
import com.linkin.common.vo.ItemVO;
import com.linkin.item.service.ItemService;
import com.linkin.item.service.ItemTagService;
import com.linkin.item.service.TagService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/items")
@Tag(name = "物品有关接口")
@Schema(name="item", description="物品相关功能")
public class ItemController {

    @Autowired
    private ItemService itemService;
    @Autowired
    private TagService tagService;
    @Autowired
    private ItemTagService itemTagService;

    /**
     * 上传物品
     * @param itemDTO
     * @return
     */
    @PostMapping("/upload")
    @Operation(summary = "上传物品")
    public ResponseEntity<?> upload(@RequestBody ItemDTO itemDTO) {
        long itemId = itemService.uploadItem(itemDTO);
        if(itemId != -1L) {
            // 批量存入新标签
            List<String> tagList = itemDTO.getTagList();
            tagService.saveBatchByName(tagList);
            QueryWrapper<com.linkin.common.entity.Tag> queryWrapper = new QueryWrapper<>();
            queryWrapper.select("id").in("name", tagList);
            // 得到所有标签
            List<Long> tagIds = tagService.listObjs(queryWrapper);
            // 批量添加物品与标签关系
            List<ItemTag> itemTags = new ArrayList<>();
            for (Long tagId : tagIds) {
                ItemTag itemTag = new ItemTag();
                itemTag.setItemId(itemId);
                itemTag.setTagId(tagId);
                itemTags.add(itemTag);
            }
            itemTagService.saveBatch(itemTags);
            return ResponseEntity.ok("Item uploaded successfully");
        } else {
            return ResponseEntity.status(403).body("Item already existed");
        }
    }

    /**
     * 根据名称模糊查询相关物品
     * @param name
     * @return
     */
    @GetMapping("/find-items-by/{name}")
    @SentinelResource(value = "getItemsByname", blockHandler = "getBlockHandler")
    @Operation(summary = "根据名称查询物品")
    public ResponseEntity<?> getItemsByname(@PathVariable("name")  String name) {
        List<Item> items = itemService.getItemsByname(name);
        List<ItemVO> itemVOS = new ArrayList<>();
        for(Item item : items) {
            ItemVO itemVO = new ItemVO();
            BeanUtils.copyProperties(item, itemVO);
            itemVO.setTagList(tagService.getNamesByIds(itemTagService.getTagsOfItem(item.getId())));
            itemVOS.add(itemVO);
        }
        if(!itemVOS.isEmpty()) {
            return ResponseEntity.status(200).body(itemVOS);
        } else {
            return ResponseEntity.status(404).body("未能查询到相关资源");
        }
    }

    /**
     * 查询所有带有该标签的物品
     * @param name
     * @return
     */
    @GetMapping("/find-items-of-tag/{tag}")
    @Operation(summary = "根据标签查询物品")
    public ResponseEntity<?> getAllItemsOftag(@PathVariable("tag")  String name) {
        Long tagId = tagService.getIdByName(name);
        List<Long> itemIds = itemTagService.getItemsByTag(tagId);
        List<Item> items = itemService.listByIds(itemIds);
        List<ItemVO> itemVOS = new ArrayList<>();
        for(Item item : items) {
            ItemVO itemVO = new ItemVO();
            BeanUtils.copyProperties(item, itemVO);
            itemVO.setTagList(tagService.getNamesByIds(itemTagService.getTagsOfItem(item.getId())));
            itemVOS.add(itemVO);
        }
        if(!itemVOS.isEmpty()) {
            return ResponseEntity.status(200).body(itemVOS);
        } else {
            return ResponseEntity.status(404).body("未能查询到相关资源");
        }
    }

    /**
     * 模拟某故障服务
     * @return
     * @throws Exception
     */
    @GetMapping("/flow")
    public String flow() throws Exception {
        Thread.sleep(3000);
        return "normal";
    }

    // 流控方法必须和原方法类型一致参数一致
    // 一定要加上BlockException
    public ResponseEntity<?> getBlockHandler(String name, BlockException blockException){
        // 我们可以在这个方法里面处理流控后的业务逻辑
        return ResponseEntity.status(403).header("content-type", "text/plain;charset=UTF-8").body("别急");
    }
}
