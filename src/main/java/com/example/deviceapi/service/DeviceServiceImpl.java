package com.example.deviceapi.service;

import com.example.deviceapi.enums.DeviceState;
import com.example.deviceapi.model.Device;
import com.example.deviceapi.repository.DeviceRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
public class DeviceServiceImpl implements DeviceService{

    private final DeviceRepository repository;


    @Override
    public Device createDevice(Device device){
        device.setId(null); //garantir que o ID nao seja setado
        device.setCreationTime(LocalDateTime.now());
        return repository.save(device);
    }

    @Override
    public Device updateDevice(Long id, Device updated){
        Device existing = getDevice(id);

        if(existing.getState() == DeviceState.IN_USE
                && (!Objects.equals(existing.getName(), updated.getName()))
                    || !Objects.equals(existing.getBrand(), updated.getBrand())){
            throw new IllegalStateException("Cannot update bane ir brand of a device in use");
        }

        updated.setId(id);
        updated.setCreationTime(existing.getCreationTime());

        return repository.save(updated);
    }

    @Override
    public Device partialUpdateDevice(Long id, Map<String, Object> updates){
        Device device = getDevice(id);

        if(device.getState() == DeviceState.IN_USE){
            if(updates.containsKey("name") || updates.containsKey("brand")){
                throw new IllegalStateException("Name and brand cannot be updated while in use.");

            }
        }

        updates.forEach((key, value) -> {
            switch (key){
                case "name" -> device.setName((String) value);
                case "brand" -> device.setBrand((String) value);
                case "state" -> device.setState(DeviceState.valueOf(value.toString()));
                case "creationTime" -> {} //ignorar
                default -> throw new IllegalArgumentException("Invalid field" + key);
            }
        });
        return repository.save(device);
    }

    @Override
    public Device getDevice(Long id){
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Device not found"));
    }

    @Override
    public List<Device> getAllDevices(){
        return repository.findAll();
    }

    @Override
    public List<Device> getDevicesByBrand(String brand){
        return repository.findByBrandIgnoreCase(brand);
    }

    @Override
    public List<Device> getDevicesByState(DeviceState state){
        return repository.findByState(state);
    }

    @Override
    public void deleteDevice(Long id){
        Device device = getDevice(id);
        if(device.getState() == DeviceState.IN_USE){
            throw new IllegalStateException("Cannot delete a device in use");
        }
        repository.delete(device);
    }

}
