package com.example.deviceapi.model;

import com.example.deviceapi.dto.DeviceRequestDTO;
import com.example.deviceapi.dto.DeviceResponseDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DeviceMapper {
    Device toEntity(DeviceRequestDTO dto);

    DeviceResponseDTO toResponse(Device entity);

    List<DeviceResponseDTO> toResponseList(List<Device> devices);
}
