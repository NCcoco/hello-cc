package com.example.mywebsite.service;

import com.example.mywebsite.model.Department;
import com.example.mywebsite.model.Role;
import com.example.mywebsite.model.User;
import com.example.mywebsite.model.UserRole;
import com.example.mywebsite.repository.DepartmentRepository;
import com.example.mywebsite.repository.RoleRepository;
import com.example.mywebsite.repository.UserRepository;
import com.example.mywebsite.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor // Lombok will generate a constructor with all final fields
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;
    private final DepartmentRepository departmentRepository; // Added for fetching department object
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public User createUser(User user, List<Long> roleIds) {
        user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));
        userRepository.insert(user); // User ID will be populated after insert

        if (!CollectionUtils.isEmpty(roleIds)) {
            assignRolesToUserInternal(user.getId(), roleIds);
        }
        // Populate transient fields for the returned user object
        return populateUserDetails(user);
    }

    public User getUserById(Long id) {
        User user = userRepository.selectById(id);
        return populateUserDetails(user);
    }

    public User getUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        return populateUserDetails(user);
    }

    public List<User> getAllUsers() {
        List<User> users = userRepository.selectList(null);
        if (CollectionUtils.isEmpty(users)) {
            return Collections.emptyList();
        }
        return users.stream()
                .map(this::populateUserDetails)
                .collect(Collectors.toList());
    }

    @Transactional
    public User updateUser(Long id, User userDetails, List<Long> roleIds) {
        User existingUser = userRepository.selectById(id);
        if (existingUser == null) {
            // Or throw an exception
            return null;
        }

        // Update basic details
        if (userDetails.getUsername() != null) {
            existingUser.setUsername(userDetails.getUsername());
        }
        if (userDetails.getEmail() != null) {
            existingUser.setEmail(userDetails.getEmail());
        }
        if (userDetails.getDepartmentId() != null) {
            existingUser.setDepartmentId(userDetails.getDepartmentId());
        }
        // Handle password update
        if (userDetails.getPasswordHash() != null && !userDetails.getPasswordHash().isEmpty()) {
            existingUser.setPasswordHash(passwordEncoder.encode(userDetails.getPasswordHash()));
        }

        userRepository.updateById(existingUser);

        // Update roles
        userRoleRepository.deleteByUserId(id); // Clear existing roles
        if (!CollectionUtils.isEmpty(roleIds)) {
            assignRolesToUserInternal(id, roleIds);
        }
        return populateUserDetails(existingUser);
    }

    @Transactional
    public void deleteUser(Long id) {
        userRoleRepository.deleteByUserId(id); // Delete role associations first
        userRepository.deleteById(id);
    }

    @Transactional
    public void assignRolesToUser(Long userId, List<Long> roleIds) {
        // Optional: Check if user exists
        if (userRepository.selectById(userId) == null) {
            // throw new ResourceNotFoundException("User not found with id " + userId);
            return;
        }
        userRoleRepository.deleteByUserId(userId); // Clear existing roles first
        assignRolesToUserInternal(userId, roleIds);
    }


    public List<Role> getUserRoles(Long userId) {
        List<UserRole> userRoles = userRoleRepository.findByUserId(userId);
        if (CollectionUtils.isEmpty(userRoles)) {
            return Collections.emptyList();
        }
        List<Long> roleIds = userRoles.stream().map(UserRole::getRoleId).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(roleIds)) {
            return Collections.emptyList();
        }
        return roleRepository.selectBatchIds(roleIds);
    }

    // Helper method to populate transient fields (roles and department)
    private User populateUserDetails(User user) {
        if (user == null) {
            return null;
        }
        // Populate roles
        user.setRoles(getUserRoles(user.getId()));

        // Populate department
        if (user.getDepartmentId() != null) {
            Department department = departmentRepository.selectById(user.getDepartmentId());
            user.setDepartment(department);
        }
        return user;
    }

    // Internal helper to avoid redundant checks if user existence is already confirmed
    private void assignRolesToUserInternal(Long userId, List<Long> roleIds) {
        if (!CollectionUtils.isEmpty(roleIds)) {
            for (Long roleId : roleIds) {
                // Optional: Check if role exists
                if (roleRepository.selectById(roleId) == null) {
                    // Log warning or throw exception
                    continue;
                }
                userRoleRepository.insert(new UserRole(userId, roleId));
            }
        }
    }
}
