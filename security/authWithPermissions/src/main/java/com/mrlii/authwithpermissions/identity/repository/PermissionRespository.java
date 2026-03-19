package com.mrlii.authwithpermissions.identity.repository;

import com.mrlii.authwithpermissions.identity.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRespository extends JpaRepository<Permission, Long> {
}
