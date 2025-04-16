package com.example.deviceapi.service;

import com.example.deviceapi.enums.DeviceState;
import com.example.deviceapi.model.Device;

import java.util.List;
import java.util.Map;

public interface DeviceService {
    //Creation time is not updatable.

    //name and brand can't be updated if state == IN_USE.

    //Devices in use cannot be deleted.

    Device createDevice(Device device);
    Device updateDevice(Long id, Device device);
    Device partialUpdateDevice(Long id, Map<String, Object> updates);
    Device getDevice(Long id);
    List<Device> getAllDevices();
    List<Device> getDevicesByBrand(String brand);
    List<Device> getDevicesByState(DeviceState state);
    void deleteDevice(Long id);
}
