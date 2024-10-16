package com.linkin.item.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;


import com.linkin.item.openfeign.UserOpenFeign;
import com.linkin.item.pojo.dto.ItemDTO;
import com.linkin.item.pojo.entity.Item;
import com.linkin.item.pojo.vo.ItemVO;
import com.linkin.item.service.ItemService;
import com.linkin.item.service.RankingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/items")
@Tag(name = "评分对象有关接口")
@Schema(name="item", description="评分对象相关功能")
public class ItemController {

    @Autowired
    private ItemService itemService;
    @Autowired
    private RankingService rankingService;
    @Qualifier("com.linkin.item.openfeign.UserOpenFeign")
    @Autowired
    private UserOpenFeign userOpenFeign;

    /**
     * 上传物品
     * @param itemDTO
     * @return
     */
    @PostMapping("/upload")
    @Operation(summary = "上传物品")
    public ResponseEntity<?> uploadItem(
            @RequestHeader("X-User-Id") Long userId,
            @RequestHeader("X-User-Name") String userName,
            @RequestBody ItemDTO itemDTO) {

        // check user existed
        if (!userOpenFeign.UserIsExisted(itemDTO.getUserId())) {
            return ResponseEntity.status(403).body("Who R U ?");
        }
        // check the ranking
        if(rankingService.getOptById(itemDTO.getRankingId()).isEmpty()) {
            return ResponseEntity.status(403).body("No such ranking");
        }
        // check the item
        QueryWrapper<Item> checkByName = new QueryWrapper<>();
        checkByName.eq("name", itemDTO.getName()).in("ranking_id", itemDTO.getRankingId());
        if(itemService.exists(checkByName)) {
            return ResponseEntity.status(403).body("Item exists");
        }
        Item newItem = new Item();
        BeanUtils.copyProperties(itemDTO, newItem);
        //newItem.setCreatedAt(LocalDateTime.now());
        itemService.save(newItem);
        return ResponseEntity.ok().body("Created successfully!");
    }

    /**
     * 根据名称模糊查询相关评分对象
     * @param name
     * @return
     */
    @GetMapping("/find-items-by/{name}")
    @SentinelResource(value = "getItemsByname", blockHandler = "getBlockHandler")
    @Operation(summary = "根据名称查询物品")
    public ResponseEntity<?> getItemsByname(@PathVariable("name")  String name) {
        List<Item> items = itemService.getItemsByname(name);
        List<ItemVO> itemVOS = itemService.listItemVO(items);
        if(!itemVOS.isEmpty()) {
            return ResponseEntity.status(200).body(itemVOS);
        } else {
            return ResponseEntity.status(404).body("未能查询到相关评分对象");
        }
    }


    @RequestMapping(value = "/check/{id}", method=RequestMethod.GET)
    @Operation(summary = "检查评分对象是否存在")
    public boolean ItemIsExisted(@PathVariable("id") long id) {
        return itemService.getById(id) != null;
    }







//    /**
//     * 模拟某故障服务
//     * @return
//     * @throws Exception
//     */
//    @GetMapping("/flow")
//    public String flow() throws Exception {
//        Thread.sleep(3000);
//        return "normal";
//    }



    // 流控方法必须和原方法类型一致参数一致
    // 一定要加上BlockException
    public ResponseEntity<?> getBlockHandler(String name, BlockException blockException){
        // 我们可以在这个方法里面处理流控后的业务逻辑
        return ResponseEntity.status(403).header("content-type", "text/plain;charset=UTF-8").body("别急");
    }
}
