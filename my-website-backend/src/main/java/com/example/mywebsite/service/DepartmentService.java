package com.example.mywebsite.service;

import com.example.mywebsite.model.Department;
import com.example.mywebsite.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public List<Department> getAllDepartments() {
        return departmentRepository.selectList(null);
    }

    public Department getDepartmentById(Long id) {
        return departmentRepository.selectById(id);
    }

    @Transactional
    public Department createDepartment(Department department) {
        // MyBatis-Plus will automatically fill createdAt and updatedAt
        departmentRepository.insert(department);
        return department;
    }

    @Transactional
    public Department updateDepartment(Long id, Department departmentDetails) {
        departmentDetails.setId(id);
        // MyBatis-Plus will automatically fill updatedAt
        departmentRepository.updateById(departmentDetails);
        return departmentDetails;
    }

    @Transactional
    public void deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
    }
}
