package com.example.deviceapi.controller;

import com.example.deviceapi.dto.DeviceRequestDTO;
import com.example.deviceapi.dto.DeviceResponseDTO;
import com.example.deviceapi.enums.DeviceState;
import com.example.deviceapi.model.DeviceMapper;
import com.example.deviceapi.service.DeviceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/devices")
@RequiredArgsConstructor
public class DeviceController {

    private final DeviceService deviceService;
    private final DeviceMapper mapper;


    @PostMapping
    public ResponseEntity<DeviceResponseDTO> create(@RequestBody @Valid DeviceRequestDTO dto) {
        var device = mapper.toEntity(dto);
        var saved = deviceService.createDevice(device);
        return new ResponseEntity<>(mapper.toResponse(saved), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DeviceResponseDTO> update(@PathVariable Long id, @RequestBody @Valid DeviceRequestDTO dto) {
        var updated = deviceService.updateDevice(id, mapper.toEntity(dto));
        return ResponseEntity.ok(mapper.toResponse(updated));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<DeviceResponseDTO> partialUpdate(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        var updated = deviceService.partialUpdateDevice(id, updates);
        return ResponseEntity.ok(mapper.toResponse(updated));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeviceResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(mapper.toResponse(deviceService.getDevice(id)));
    }

    @GetMapping
    public ResponseEntity<List<DeviceResponseDTO>> getAll(
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) DeviceState state
    ) {
        if (brand != null) return ResponseEntity.ok(mapper.toResponseList(deviceService.getDevicesByBrand(brand)));
        if (state != null) return ResponseEntity.ok(mapper.toResponseList(deviceService.getDevicesByState(state)));
        return ResponseEntity.ok(mapper.toResponseList(deviceService.getAllDevices()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        deviceService.deleteDevice(id);
        return ResponseEntity.noContent().build();
    }
}
