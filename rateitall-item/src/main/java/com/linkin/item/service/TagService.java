package com.linkin.item.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.linkin.common.entity.Item;
import com.linkin.common.entity.Tag;
import org.springframework.stereotype.Service;

import java.util.List;


public interface TagService extends IService<Tag> {

    void saveBatchByName(List<String> tagList);
}
