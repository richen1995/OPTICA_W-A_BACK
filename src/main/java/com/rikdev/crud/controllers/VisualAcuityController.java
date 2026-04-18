package com.rikdev.crud.controllers;

import com.rikdev.crud.entities.VisualAcuity;
import com.rikdev.crud.services.VisualAcuityServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/visual_acuities")

public class VisualAcuityController {
    private static final Logger logger = LoggerFactory.getLogger(VisualAcuityController.class);

    @Autowired
    VisualAcuityServiceImpl visualAcuityServiceImpl;

    @PostMapping            //GUARDAR LIBRO
    public ResponseEntity<VisualAcuity> saveVisualAcuity(@RequestBody VisualAcuity visualAcuity){
        try {
            logger.info("Entidad que se va a almacenar: {}", visualAcuity);
            VisualAcuity saveVisualAcuity = visualAcuityServiceImpl.saveVisualAcuity(visualAcuity);
            logger.info("Se se ha guardado la visualAcuitya en la base de datos: {}", saveVisualAcuity);
            return new ResponseEntity<>(saveVisualAcuity, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error inesperado: {}", e.getMessage(), e);
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<VisualAcuity> updateVisualAcuity(@RequestBody VisualAcuity visualAcuity){
        try {
            if(visualAcuity.getId_visual_acuity() != null && visualAcuityServiceImpl.getVisualAcuityById(visualAcuity.getId_visual_acuity()).isPresent()){
                logger.info("Actualizando la visualAcuity con ID: {}", visualAcuity.getId_visual_acuity());
                VisualAcuity updatedVisualAcuity = visualAcuityServiceImpl.updateVisualAcuity(visualAcuity);
                return new ResponseEntity<>(updatedVisualAcuity, HttpStatus.OK);
            } else {
                logger.warn("No se puede actualizar. La visualAcuity con ID: {} no existe.", visualAcuity.getId_visual_acuity());
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<VisualAcuity>> getAllVisualAcuitys(){
        return new ResponseEntity<>(visualAcuityServiceImpl.getVisualAcuity(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    /*public ResponseEntity<VisualAcuity> getVisualAcuityById(@PathVariable Long id){*/
    public ResponseEntity<List<VisualAcuity>> getVisualAcuityById(@PathVariable Long id) {
        Optional<VisualAcuity> visualAcuity = visualAcuityServiceImpl.getVisualAcuityById(id);
        /*return visualAcuity.map(value ->new ResponseEntity<>(value,HttpStatus.OK)).orElseGet(() ->
                new ResponseEntity<>(HttpStatus.NOT_FOUND));*/

        // Si el dispositivo existe, se devuelve como un arreglo
        if (visualAcuity.isPresent()) {
            return new ResponseEntity<>(List.of(visualAcuity.get()), HttpStatus.OK);
        }

        // Si no existe, se devuelve un arreglo vacío
        return new ResponseEntity<>(List.of(), HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")            //ELIMINAR UN LIBRO POR ID
    public ResponseEntity<VisualAcuity> deleteVisualAcuity(@PathVariable Long id){
        Optional <VisualAcuity> visualAcuity = visualAcuityServiceImpl.getVisualAcuityById(id);
        if(visualAcuity.isPresent()){
            visualAcuityServiceImpl.deleteVisualAcuity(visualAcuity.get().getId_visual_acuity());
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/search") //BUSQUEDA DE LOS DISPOSITIVOS POR UNA PALABRA CLAVE
    public ResponseEntity<List<VisualAcuity>> getVisualAcuityKeyWord(@RequestParam  String keyword) {
        List<VisualAcuity> visualAcuitys = visualAcuityServiceImpl.searchByDescription(keyword);
        /*return visualAcuity.map(value ->new ResponseEntity<>(value,HttpStatus.OK)).orElseGet(() ->
                new ResponseEntity<>(HttpStatus.NOT_FOUND));*/

        // Si el dispositivo existe, se devuelve como un arreglo
        if (!visualAcuitys.isEmpty()) {
            return new ResponseEntity<>(visualAcuitys, HttpStatus.OK);
        }

        // Si no existe, se devuelve un arreglo vacío
        return new ResponseEntity<>(List.of(), HttpStatus.NOT_FOUND);
    }



}
