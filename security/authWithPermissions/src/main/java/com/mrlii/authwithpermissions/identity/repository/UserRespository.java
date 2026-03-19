package com.mrlii.authwithpermissions.identity.repository;

import com.mrlii.authwithpermissions.identity.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRespository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    boolean existsByEmail(String email);
}
