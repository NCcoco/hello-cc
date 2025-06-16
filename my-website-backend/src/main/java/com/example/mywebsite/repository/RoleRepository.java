package com.example.mywebsite.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mywebsite.model.Role;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoleRepository extends BaseMapper<Role> {
}
