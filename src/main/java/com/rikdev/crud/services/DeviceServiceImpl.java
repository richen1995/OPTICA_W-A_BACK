package com.rikdev.crud.services;

import com.rikdev.crud.entities.Device;
import com.rikdev.crud.repositories.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeviceServiceImpl implements  DeviceService{

    @Autowired
    DeviceRepository deviceRepository;

    @Override
    public Device saveDevice(Device device){
        return deviceRepository.save(device);
    }

    @Override
    public Device updateDevice(Device device){
        return deviceRepository.save(device);
    }

    @Override
    public List<Device> getDevices(){
        return deviceRepository.findAll();
    }

    @Override
    public Optional<Device> getDeviceById(Long id){
        return deviceRepository.findById(id);
    }

    @Override
    public void deleteDevice(Long id){
        deviceRepository.deleteById(id);
    }

    @Override
    public List<Device> searchByDescription(String keyword) {
        System.out.println("Buscando dispositivos con el término:" + keyword);
        /*keyword = "VALIEN";*/
        return deviceRepository.searchByDescription(keyword);
    }
}
