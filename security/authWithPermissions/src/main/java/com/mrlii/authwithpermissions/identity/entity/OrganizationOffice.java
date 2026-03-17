package com.mrlii.authwithpermissions.identity.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table( name = "organization_offices" )
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrganizationOffice extends BaseEntity {

    private String name;

    @ManyToOne( fetch = FetchType.LAZY )
    private Organization organization;
}
