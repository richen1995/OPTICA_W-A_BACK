package com.rikdev.crud.controllers;

import com.rikdev.crud.entities.Refraction;
import com.rikdev.crud.services.RefractionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/refraction")
public class RefractionController {

    private static final Logger logger = LoggerFactory.getLogger(RefractionController.class);

    @Autowired
    private RefractionService refractionService;

    @PostMapping
    public ResponseEntity<Refraction> saveRefraction(@RequestBody Refraction refraction) {
        try {
            logger.info("Guardando refracción: {}", refraction);
            Refraction savedRefraction = refractionService.saveRefraction(refraction);
            return new ResponseEntity<>(savedRefraction, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error al guardar refracción: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<Refraction> updateRefraction(@RequestBody Refraction refraction) {
        try {
            if (refraction.getId_refraction() != null && refractionService.getRefractionById(refraction.getId_refraction()).isPresent()) {
                logger.info("Actualizando refracción con ID: {}", refraction.getId_refraction());
                Refraction updatedRefraction = refractionService.updateRefraction(refraction);
                return new ResponseEntity<>(updatedRefraction, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<Refraction>> getAllRefractions() {
        return new ResponseEntity<>(refractionService.getRefractions(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Refraction>> getRefractionById(@PathVariable Long id) {
        Optional<Refraction> refraction = refractionService.getRefractionById(id);
        if (refraction.isPresent()) {
            return new ResponseEntity<>(List.of(refraction.get()), HttpStatus.OK);
        }
        return new ResponseEntity<>(List.of(), HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRefraction(@PathVariable Long id) {
        if (refractionService.getRefractionById(id).isPresent()) {
            refractionService.deleteRefraction(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
