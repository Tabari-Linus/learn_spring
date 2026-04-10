package com.mrlii.tddtaskmanagementapplication.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record TaskDto(
        Long id,
        @NotEmpty @Size(min = 3, max = 255) String title,
        String status
) {
}
