package com.example.mywebsite.controller;

import com.example.mywebsite.dto.AuthResponse;
import com.example.mywebsite.dto.LoginRequest;
import com.example.mywebsite.model.Role;
import com.example.mywebsite.model.User;
import com.example.mywebsite.security.jwt.JwtTokenProvider;
import com.example.mywebsite.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager; // Will be configured in SecurityConfig
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider; // Placeholder, will be implemented

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Generate JWT token using the placeholder provider
        // In a real scenario, jwtTokenProvider.generateToken would take more details from 'authentication'
        // or the UserDetails object it contains.
        String token = jwtTokenProvider.generateToken(authentication);

        User user = userService.getUserByUsername(loginRequest.getUsername());
        List<String> roles = user.getRoles().stream().map(Role::getName).collect(Collectors.toList());

        return ResponseEntity.ok(new AuthResponse(token, user.getId(), user.getUsername(), roles));
    }
}
