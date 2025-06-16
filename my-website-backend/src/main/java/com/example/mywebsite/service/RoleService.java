package com.example.mywebsite.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mywebsite.model.Role;
import com.example.mywebsite.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public List<Role> getAllRoles() {
        return roleRepository.selectList(null);
    }

    public Role getRoleById(Long id) {
        return roleRepository.selectById(id);
    }

    @Transactional
    public Role createRole(Role role) {
        // Consider if dynamic role creation is needed or if roles are static.
        // MyBatis-Plus will automatically fill createdAt and updatedAt
        roleRepository.insert(role);
        return role;
    }

    @Transactional
    public Role updateRole(Long id, Role roleDetails) {
        // Consider if dynamic role update is needed.
        roleDetails.setId(id);
        // MyBatis-Plus will automatically fill updatedAt
        roleRepository.updateById(roleDetails);
        return roleDetails;
    }

    @Transactional
    public void deleteRole(Long id) {
        // Consider if dynamic role deletion is needed.
        // Also, consider implications if roles are assigned to users.
        roleRepository.deleteById(id);
    }

    public Role findByName(String name) {
        return roleRepository.selectOne(new QueryWrapper<Role>().eq("name", name));
    }
}
