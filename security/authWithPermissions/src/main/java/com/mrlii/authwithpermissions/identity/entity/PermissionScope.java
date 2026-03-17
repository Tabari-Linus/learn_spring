package com.mrlii.authwithpermissions.identity.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table( name = "permission_scopes" )
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PermissionScope extends BaseEntity {

    private String name;

    @ManyToOne
    @JoinColumn(name = "permission_id")
    private Permission permission;

    @ManyToOne
    @JoinColumn(name = "access_level_id")
    private AccessLevel accessLevel;

    @ManyToOne
    @JoinColumn(name = "organization_office_id")
    private OrganizationOffice organizationOffice;
}
