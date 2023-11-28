package com.example.adf_project_2.controllers.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record NewUserDTO(
        @NotEmpty(message = "User email cannot be empty!")
        @NotBlank(message = "User email cannot be blank!")
        @NotNull(message = "User email cannot be null!")
        String userEmail,
        @NotEmpty(message = "User password cannot be empty!")
        @NotBlank(message = "User password cannot be blank!")
        @NotNull(message = "User password cannot be null!")
        String userPassword,
        @NotNull(message = "User locked cannot be null!")
        Boolean userLocked,
        @NotEmpty(message = "User role cannot be empty!")
        @NotBlank(message = "User role cannot be blank!")
        @NotNull(message = "User role cannot be null!")
        String userRole,
        @NotEmpty(message = "User phone number cannot be empty!")
        @NotBlank(message = "User phone number cannot be blank!")
        @NotNull(message = "User phone number cannot be null!")
        String userPhoneNumber,
        @NotEmpty(message = "User PPSN cannot be empty!")
        @NotBlank(message = "User PPSN cannot be blank!")
        @NotNull(message = "User PPSN cannot be null!")
        String userPpsn
) {
}
