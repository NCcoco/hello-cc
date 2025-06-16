package com.example.mywebsite.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mywebsite.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRepository extends BaseMapper<User> {

    default User findByUsername(String username) {
        return selectOne(new QueryWrapper<User>().eq("username", username));
    }
}
