package com.example.adf_project_2.controllers.dtos;

import jakarta.validation.constraints.*;

public record NewUserDTO(
        @NotEmpty(message = "User email cannot be empty!")
        @NotBlank(message = "User email cannot be blank!")
        @NotNull(message = "User email cannot be null!")
        @Email(message = "User email has to be a valid email!")
        String userEmail,
        @NotEmpty(message = "User password cannot be empty!")
        @NotBlank(message = "User password cannot be blank!")
        @NotNull(message = "User password cannot be null!")
        @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,20}$", message = "Password must contain uppercase, lowercase and digits and be at least 8 characters long!")
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
