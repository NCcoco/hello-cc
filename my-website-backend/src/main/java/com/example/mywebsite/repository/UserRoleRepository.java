package com.example.mywebsite.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mywebsite.model.UserRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserRoleRepository extends BaseMapper<UserRole> {

    default void deleteByUserId(Long userId) {
        delete(new QueryWrapper<UserRole>().eq("user_id", userId));
    }

    default List<UserRole> findByUserId(Long userId) {
        return selectList(new QueryWrapper<UserRole>().eq("user_id", userId));
    }
}
