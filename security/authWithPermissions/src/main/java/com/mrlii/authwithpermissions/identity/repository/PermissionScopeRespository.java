package com.mrlii.authwithpermissions.identity.repository;

import com.mrlii.authwithpermissions.identity.entity.PermissionScope;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionScopeRespository extends JpaRepository<PermissionScope, Long> {
}
