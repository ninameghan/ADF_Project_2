package com.example.adf_project_2.controllers.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record NewTenantDTO(
        @NotEmpty(message = "Tenant name cannot be empty!")
        @NotBlank(message = "Tenant names cannot be blank!")
        @NotNull(message = "Tenant name cannot be null!")
        String tenantName,
        @NotEmpty(message = "Tenant email cannot be empty!")
        @NotBlank(message = "Tenant email cannot be blank!")
        @NotNull(message = "Tenant email cannot be null!")
        String tenantEmail,
        @NotEmpty(message = "Tenant phone number cannot be empty!")
        @NotBlank(message = "Tenant phone number cannot be blank!")
        @NotNull(message = "Tenant phone number cannot be null!")
        String tenantPhoneNumber,
        @Min(value = 0, message = "Invalid property ID!")
        @NotNull(message = "Property ID cannot be null!")
        Integer propertyId
) {
}
