package com.example.adf_project_2.controllers.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record MoveTenantDTO(
        @Min(value = 0, message = "Invalid property ID!")
        @NotNull(message = "Property ID cannot be null!")
        Integer newPropertyId
) {
}
