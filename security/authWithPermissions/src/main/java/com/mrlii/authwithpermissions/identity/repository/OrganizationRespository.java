package com.mrlii.authwithpermissions.identity.repository;

import com.mrlii.authwithpermissions.identity.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRespository extends JpaRepository<Organization, Long> {
}
