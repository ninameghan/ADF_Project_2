package com.example.adf_project_2.controllers.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record NewPropertyDTO(
        @NotEmpty(message = "Property address cannot be empty!")
        @NotBlank(message = "Property address cannot be blank!")
        @NotNull(message = "Property address cannot be null!")
        String propertyAddress,
        @NotEmpty(message = "Property eir code cannot be empty!")
        @NotBlank(message = "Property eir code cannot be blank!")
        @NotNull(message = "Property eir code cannot be null!")
        String propertyEircode,
        @Min(value = 1, message = "Property capacity must be minimum 1!")
        @NotNull(message = "Property capacity cannot be null!")
        int propertyCapacity,
        @Min(value = 1, message = "Property monthly cost must be minimum 1!")
        @NotNull(message = "Property monthly cost cannot be null!")
        double propertyMonthlyCost
) {}
