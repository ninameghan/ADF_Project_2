package com.example.adf_project_2.controllers.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record NewRentDTO(
        @Min(value = 1, message = "Property monthly cost must be minimum 1!")
        @NotNull(message = "Property monthly cost cannot be null!")
        Double newRent
) {
}
