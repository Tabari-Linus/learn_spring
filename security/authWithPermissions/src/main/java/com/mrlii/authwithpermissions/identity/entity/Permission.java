package com.mrlii.authwithpermissions.identity.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table( name = "permissions" )
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Permission extends BaseEntity {

    private String name;

    private String description;

    private String groupName;
}
