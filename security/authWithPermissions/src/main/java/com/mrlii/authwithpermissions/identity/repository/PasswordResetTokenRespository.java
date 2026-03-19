package com.mrlii.authwithpermissions.identity.repository;

import com.mrlii.authwithpermissions.identity.entity.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordResetTokenRespository extends JpaRepository<PasswordResetToken, Long> {
}
