package com.mrlii.springdatajpa_library_mgt_sys.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateAuthorRequest(
        @NotBlank(message = "Author name cannot be blank")
        @Size(min = 2, max = 100, message = "Author name must be between 3 and 100 characters")
        String name,

        @NotBlank(message = "Author nationality cannot be blank")
        String nationality
) {
}
