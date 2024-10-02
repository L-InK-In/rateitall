package com.linkin.item.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.linkin.common.entity.Tag;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TagMapper extends BaseMapper<Tag> {

    void insertBatchByName(@Param("tags")List<String> tags);
}
