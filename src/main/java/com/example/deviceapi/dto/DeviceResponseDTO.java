package com.example.deviceapi.dto;

import com.example.deviceapi.enums.DeviceState;

import java.time.LocalDateTime;

public record DeviceResponseDTO(
        Long id,
        String name,
        String brand,
        DeviceState state,
        LocalDateTime creationTime
) {
}
