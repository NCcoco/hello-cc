package com.example.mywebsite.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mywebsite.model.Dictionary;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DictionaryRepository extends BaseMapper<Dictionary> {
    // Basic CRUD operations are inherited from BaseMapper
    // Custom query methods can be added here if needed
}
