package com.example.mywebsite.service;

import com.example.mywebsite.model.User;
import com.example.mywebsite.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserService userService; // To get populated roles

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        // Fetch fully populated user details, including roles
        User populatedUser = userService.getUserByUsername(username);
        if (populatedUser == null || populatedUser.getRoles() == null) {
             throw new UsernameNotFoundException("User roles could not be loaded for username: " + username);
        }


        List<GrantedAuthority> authorities = populatedUser.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(
                populatedUser.getUsername(),
                populatedUser.getPasswordHash(),
                authorities
        );
    }
}
