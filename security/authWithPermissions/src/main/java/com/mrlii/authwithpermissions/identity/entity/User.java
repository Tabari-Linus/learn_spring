package com.mrlii.authwithpermissions.identity.entity;

import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;

public class User extends BaseEntity {

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private boolean isEnabled;

    @ManyToOne(fetch = FetchType.LAZY)
    private AccessLevel accessLevel;

    @ManyToOne(fetch = FetchType.LAZY)
    private OrganizationOffice organizationOffice;
}
