package com.rikdev.crud.controllers;

import com.rikdev.crud.entities.Lensometry;
import com.rikdev.crud.services.LensometryServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/lensometry")

public class LensometryController {
    // Crear una instancia de Logger
    private static final Logger logger = LoggerFactory.getLogger(LensometryController.class);

    @Autowired
    LensometryServiceImpl lensometryServiceImpl;

    @PostMapping            //GUARDAR LENSOMETRIA
    public ResponseEntity<Lensometry> saveLensometry(@RequestBody Lensometry lensometry){
        try {
            logger.info("Entidad que se va a almacenar: {}", lensometry);
            Lensometry saveLensometry = lensometryServiceImpl.saveLensometry(lensometry);
            logger.info("Se se ha guardado la lensometrya en la base de datos: {}", saveLensometry);
            return new ResponseEntity<>(saveLensometry, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error inesperado: {}", e.getMessage(), e);
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<Lensometry> updateLensometry(@RequestBody Lensometry lensometry){
        try {
            if(lensometry.getId_lensometry() != null && lensometryServiceImpl.getLensometryById(lensometry.getId_lensometry()).isPresent()){
                logger.info("Actualizando la lensometry con ID: {}", lensometry.getId_lensometry());
                Lensometry updatedLensometry = lensometryServiceImpl.updateLensometry(lensometry);
                return new ResponseEntity<>(updatedLensometry, HttpStatus.OK);
            } else {
                logger.warn("No se puede actualizar. La lensometry con ID: {} no existe.", lensometry.getId_lensometry());
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping            //OBTENER TODOS LOS LENSOMETRIAS
    public ResponseEntity<List<Lensometry>> getAllLensometrys(){
        return new ResponseEntity<>(lensometryServiceImpl.getLensometry(),HttpStatus.OK);
    }

    @GetMapping("/{id}")            //OBTENER UN DISPOSITIVO POR ID
    /*public ResponseEntity<Lensometry> getLensometryById(@PathVariable Long id){*/
    public ResponseEntity<List<Lensometry>> getLensometryById(@PathVariable Long id) {
        Optional<Lensometry> lensometry = lensometryServiceImpl.getLensometryById(id);
        /*return lensometry.map(value ->new ResponseEntity<>(value,HttpStatus.OK)).orElseGet(() ->
                new ResponseEntity<>(HttpStatus.NOT_FOUND));*/

        // Si el dispositivo existe, se devuelve como un arreglo
        if (lensometry.isPresent()) {
            return new ResponseEntity<>(List.of(lensometry.get()), HttpStatus.OK);
        }

        // Si no existe, se devuelve un arreglo vacío
        return new ResponseEntity<>(List.of(), HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")            //ELIMINAR UN LENSOMETRIA POR ID
    public ResponseEntity<Lensometry> deleteLensometry(@PathVariable Long id){
        Optional <Lensometry> lensometry = lensometryServiceImpl.getLensometryById(id);
        if(lensometry.isPresent()){
            lensometryServiceImpl.deleteLensometry(lensometry.get().getId_medical_record());
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/search") //BUSQUEDA DE LOS DISPOSITIVOS POR UNA PALABRA CLAVE
    public ResponseEntity<List<Lensometry>> getLensometryKeyWord(@RequestParam  String keyword) {
        List<Lensometry> lensometrys = lensometryServiceImpl.searchByDescription(keyword);
        /*return lensometry.map(value ->new ResponseEntity<>(value,HttpStatus.OK)).orElseGet(() ->
                new ResponseEntity<>(HttpStatus.NOT_FOUND));*/

        // Si el dispositivo existe, se devuelve como un arreglo
        if (!lensometrys.isEmpty()) {
            return new ResponseEntity<>(lensometrys, HttpStatus.OK);
        }

        // Si no existe, se devuelve un arreglo vacío
        return new ResponseEntity<>(List.of(), HttpStatus.NOT_FOUND);
    }
}
