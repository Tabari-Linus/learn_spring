package com.mrlii.authwithpermissions.identity.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Table( name = "access_levels" )
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AccessLevel extends BaseEntity {

        private String name;

        @OneToMany( fetch = FetchType.LAZY )
        private List<Permission> permissions;

        @OneToMany( fetch = FetchType.LAZY )
        private List<PermissionScope> permissionScopes;

        @OneToMany( fetch = FetchType.LAZY )
        private List<User> users;
}
