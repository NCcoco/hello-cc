package com.example.mywebsite.controller;

import com.example.mywebsite.dto.UserCreateRequest;
import com.example.mywebsite.dto.UserUpdateRequest;
import com.example.mywebsite.model.User;
import com.example.mywebsite.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody UserCreateRequest userCreateRequest) {
        User user = new User();
        user.setUsername(userCreateRequest.getUsername());
        user.setPasswordHash(userCreateRequest.getPassword()); // Service will encode it
        user.setEmail(userCreateRequest.getEmail());
        user.setDepartmentId(userCreateRequest.getDepartmentId());

        User createdUser = userService.createUser(user, userCreateRequest.getRoleIds());
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @Valid @RequestBody UserUpdateRequest userUpdateRequest) {
        User user = new User();
        user.setUsername(userUpdateRequest.getUsername());
        user.setPasswordHash(userUpdateRequest.getPassword()); // Service will encode if present
        user.setEmail(userUpdateRequest.getEmail());
        user.setDepartmentId(userUpdateRequest.getDepartmentId());

        User updatedUser = userService.updateUser(id, user, userUpdateRequest.getRoleIds());
        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
