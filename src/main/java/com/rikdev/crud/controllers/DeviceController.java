package com.rikdev.crud.controllers;


import com.rikdev.crud.entities.Device;
import com.rikdev.crud.services.DeviceServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/device")

public class DeviceController {

    // Crear una instancia de Logger
    private static final Logger logger = LoggerFactory.getLogger(DeviceController.class);

    @Autowired
    DeviceServiceImpl deviceServiceImpl;

    @PostMapping            //GUARDAR LIBRO
    public ResponseEntity<Device> saveDevice(@RequestBody Device device){
        try {
            Device saveDevice = deviceServiceImpl.saveDevice(device);
            logger.info("Se se ha guardado el dispositivo");
            System.out.println("Hola, este es un mensaje en la consola.");
            return new ResponseEntity<>(saveDevice, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error inesperado: {}", e.getMessage(), e);
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping            //ACTUALIZAR LIBRO
    public ResponseEntity<Device> updateDevice(@RequestBody Device device){
        try {
            Device saveDevice = deviceServiceImpl.updateDevice(device);
            return new ResponseEntity<>(saveDevice, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping            //OBTENER TODOS LOS LIBROS
    public ResponseEntity<List<Device>> getAllDevices(){
        return new ResponseEntity<>(deviceServiceImpl.getDevices(),HttpStatus.OK);
    }

    @GetMapping("/{id}")            //OBTENER UN DISPOSITIVO POR ID
    /*public ResponseEntity<Device> getDeviceById(@PathVariable Long id){*/
    public ResponseEntity<List<Device>> getDeviceById(@PathVariable Long id) {
        Optional<Device> device = deviceServiceImpl.getDeviceById(id);
        /*return device.map(value ->new ResponseEntity<>(value,HttpStatus.OK)).orElseGet(() ->
                new ResponseEntity<>(HttpStatus.NOT_FOUND));*/

        // Si el dispositivo existe, se devuelve como un arreglo
        if (device.isPresent()) {
            return new ResponseEntity<>(List.of(device.get()), HttpStatus.OK);
        }

        // Si no existe, se devuelve un arreglo vacío
        return new ResponseEntity<>(List.of(), HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")            //ELIMINAR UN LIBRO POR ID
    public ResponseEntity<Device> deleteDevice(@PathVariable Long id){
        Optional <Device> device = deviceServiceImpl.getDeviceById(id);
        if(device.isPresent()){
            deviceServiceImpl.deleteDevice(device.get().getId_device());
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/search") //BUSQUEDA DE LOS DISPOSITIVOS POR UNA PALABRA CLAVE
    public ResponseEntity<List<Device>> getDeviceKeyWord(@RequestParam  String keyword) {
        List<Device> devices = deviceServiceImpl.searchByDescription(keyword);
        /*return device.map(value ->new ResponseEntity<>(value,HttpStatus.OK)).orElseGet(() ->
                new ResponseEntity<>(HttpStatus.NOT_FOUND));*/

        // Si el dispositivo existe, se devuelve como un arreglo
        if (!devices.isEmpty()) {
            return new ResponseEntity<>(devices, HttpStatus.OK);
        }

        // Si no existe, se devuelve un arreglo vacío
        return new ResponseEntity<>(List.of(), HttpStatus.NOT_FOUND);
    }
}
