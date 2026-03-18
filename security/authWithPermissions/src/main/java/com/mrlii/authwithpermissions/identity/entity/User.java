package com.mrlii.authwithpermissions.identity.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User extends BaseEntity {

    private String firstName;

    private String lastName;

    @Email
    private String email;

    @Size(min = 6, message = "Password must be at least 6 characters long")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d]+$", message = "Password must contain at least one letter, one number, and one special character")
    private String password;

    private boolean isEnabled;

    @ManyToOne(fetch = FetchType.LAZY)
    private AccessLevel accessLevel;

    @ManyToOne(fetch = FetchType.LAZY)
    private OrganizationOffice organizationOffice;
}
