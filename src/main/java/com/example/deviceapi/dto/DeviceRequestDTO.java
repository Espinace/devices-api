package com.example.deviceapi.dto;

import com.example.deviceapi.enums.DeviceState;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DeviceRequestDTO(
        @NotBlank String name,
        @NotBlank String brand,
        @NotNull DeviceState state
) {
}
