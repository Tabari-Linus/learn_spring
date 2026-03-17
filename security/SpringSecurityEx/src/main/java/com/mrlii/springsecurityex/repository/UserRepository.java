package com.mrlii.springsecurityex.repository;

import com.mrlii.springsecurityex.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {
    Users findByUsername(String username);
}
