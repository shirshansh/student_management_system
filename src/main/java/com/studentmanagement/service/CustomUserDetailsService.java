package com.studentmanagement.service;

import com.studentmanagement.entity.CustomUser;
import com.studentmanagement.repository.CustomUserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService
        implements UserDetailsService {

    private final CustomUserRepository customUserRepository;

    public CustomUserDetailsService(
            CustomUserRepository customUserRepository) {

        this.customUserRepository = customUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(
            String username) {

        CustomUser customUser =
                customUserRepository
                        .findByUsername(username)
                        .orElseThrow(() -> new UsernameNotFoundException("Username = " + username + " not found"));

        return User.builder()
                .username(customUser.getUsername())
                .password(customUser.getPassword())
                .roles(customUser.getRole())
                .build();
    }
}
