package com.mrlii.authwithpermissions.identity.dto.request;

import jakarta.validation.constraints.NotBlank;


public record UserRegistrationRequest(

        @NotBlank(message = "First name is required")
        String firstName,
        @NotBlank(message = "Last name is required")
        String lastName,
        @NotBlank(message = "Email is required")
        String email,
        @NotBlank(message = "Password is required")
        String password,
        @NotBlank(message="Access Level")
        Long accessLevelId,
        @NotBlank(message="Organization Office")
        Long organizationOfficeId
) {
}
