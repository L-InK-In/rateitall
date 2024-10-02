package com.linkin.item.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.linkin.common.dto.ItemDTO;
import com.linkin.common.entity.ItemTag;
import com.linkin.item.service.ItemService;
import com.linkin.item.service.ItemTagService;
import com.linkin.item.service.TagService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @Parameter(ref = "物体信息")
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
}
