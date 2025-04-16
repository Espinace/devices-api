package com.example.deviceapi.repository;

import com.example.deviceapi.enums.DeviceState;
import com.example.deviceapi.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {
    List<Device> findByBrandIgnoreCase(String brand);
    List<Device> findByState(DeviceState state);
}
