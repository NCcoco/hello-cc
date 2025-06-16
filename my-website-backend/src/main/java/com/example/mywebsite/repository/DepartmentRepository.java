package com.example.mywebsite.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mywebsite.model.Department;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DepartmentRepository extends BaseMapper<Department> {
}
