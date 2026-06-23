package com.studentmanagement.repository;

import com.studentmanagement.entity.CustomUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomUserRepository extends JpaRepository<CustomUser, Integer> {

    Optional<CustomUser> findByUsername(String username);
}
