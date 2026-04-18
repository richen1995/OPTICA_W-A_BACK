package com.rikdev.crud.controllers;

import com.rikdev.crud.entities.MedicalRecord;
import com.rikdev.crud.entities.Person;
import com.rikdev.crud.services.MedicalRecordServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/medicalRecord")

public class MedicalRecordController {

    // Crear una instancia de Logger
    private static final Logger logger = LoggerFactory.getLogger(MedicalRecordController.class);

    @Autowired
    MedicalRecordServiceImpl medicalRecordServiceImpl;

    @PostMapping // GUARDAR LIBRO
    public ResponseEntity<MedicalRecord> saveMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        try {
            MedicalRecord saveMedicalRecord = medicalRecordServiceImpl.saveMedicalRecord(medicalRecord);
            logger.info("Se se ha guardado la medicalRecorda en la base de datos");
            return new ResponseEntity<>(saveMedicalRecord, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error inesperado: {}", e.getMessage(), e);
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/full")
    public ResponseEntity<MedicalRecord> saveFullMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        try {
            MedicalRecord fullMedicalRecord = medicalRecordServiceImpl.saveFullMedicalRecord(medicalRecord);
            logger.info("Se ha guardado la historia clínica completa con sus datos relacionados.");
            return new ResponseEntity<>(fullMedicalRecord, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error al guardar historia clínica completa: {}", e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/full")
    public ResponseEntity<MedicalRecord> updateFullMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        try {
            if (medicalRecord.getId_medical_record() != null && medicalRecordServiceImpl
                    .getMedicalRecordById(medicalRecord.getId_medical_record()).isPresent()) {
                logger.info("Actualizando la historia clínica completa con ID: {}", medicalRecord.getId_medical_record());
                MedicalRecord updatedMedicalRecord = medicalRecordServiceImpl.saveFullMedicalRecord(medicalRecord);
                return new ResponseEntity<>(updatedMedicalRecord, HttpStatus.OK);
            } else {
                logger.warn("No se puede actualizar. ID no proporcionado o no existe.");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.error("Error al actualizar historia clínica completa: {}", e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping // ACTUALIZAR LIBRO
    public ResponseEntity<MedicalRecord> updateMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        try {
            if (medicalRecord.getId_medical_record() != null && medicalRecordServiceImpl
                    .getMedicalRecordById(medicalRecord.getId_medical_record()).isPresent()) {
                logger.info("Actualizando la medicalRecord con ID: {}", medicalRecord.getId_medical_record());
                MedicalRecord updatedMedicalRecord = medicalRecordServiceImpl.updateMedicalRecord(medicalRecord);
                return new ResponseEntity<>(updatedMedicalRecord, HttpStatus.OK);
            } else {
                logger.warn("No se puede actualizar. La medicalRecord con ID: {} no existe.",
                        medicalRecord.getId_medical_record());
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<MedicalRecord>> getAllMedicalRecords() {
        return new ResponseEntity<>(medicalRecordServiceImpl.getMedicalRecords(), HttpStatus.OK);
    }

    @GetMapping("/{id}") // OBTENER UN DISPOSITIVO POR ID
    /*
     * public ResponseEntity<MedicalRecord> getMedicalRecordById(@PathVariable Long
     * id){
     */
    public ResponseEntity<List<MedicalRecord>> getMedicalRecordById(@PathVariable Long id) {
        Optional<MedicalRecord> medicalRecord = medicalRecordServiceImpl.getMedicalRecordById(id);
        /*
         * return medicalRecord.map(value ->new
         * ResponseEntity<>(value,HttpStatus.OK)).orElseGet(() ->
         * new ResponseEntity<>(HttpStatus.NOT_FOUND));
         */

        // Si el dispositivo existe, se devuelve como un arreglo
        if (medicalRecord.isPresent()) {
            return new ResponseEntity<>(List.of(medicalRecord.get()), HttpStatus.OK);
        }

        // Si no existe, se devuelve un arreglo vacío
        return new ResponseEntity<>(List.of(), HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}") // ELIMINAR UN LIBRO POR ID
    public ResponseEntity<MedicalRecord> deleteMedicalRecord(@PathVariable Long id) {
        Optional<MedicalRecord> medicalRecord = medicalRecordServiceImpl.getMedicalRecordById(id);
        if (medicalRecord.isPresent()) {
            medicalRecordServiceImpl.deleteMedicalRecord(medicalRecord.get().getId_medical_record());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/search") // BUSQUEDA DE LOS DISPOSITIVOS POR UNA PALABRA CLAVE
    public ResponseEntity<List<MedicalRecord>> getMedicalRecordKeyWord(@RequestParam String keyword) {
        List<MedicalRecord> medicalRecords = medicalRecordServiceImpl.searchByDescription(keyword);
        /*
         * return medicalRecord.map(value ->new
         * ResponseEntity<>(value,HttpStatus.OK)).orElseGet(() ->
         * new ResponseEntity<>(HttpStatus.NOT_FOUND));
         */

        // Si el dispositivo existe, se devuelve como un arreglo
        if (!medicalRecords.isEmpty()) {
            return new ResponseEntity<>(medicalRecords, HttpStatus.OK);
        }

        // Si no existe, se devuelve un arreglo vacío
        return new ResponseEntity<>(List.of(), HttpStatus.NOT_FOUND);
    }

    /*
     * SRV QUE DEVUELVE LAS HISTORIAS EN UN RANGO DE FECHA E IDENTIFICACION
     * DETERMINADA
     */
    @GetMapping("/searchFilter")
    public ResponseEntity<List<MedicalRecord>> getSearchMedicalRecord(
            @RequestParam(required = false) String identification,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fdesde,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fhasta,
            @RequestParam(required = false) String name) {

        List<MedicalRecord> medicalRecords = medicalRecordServiceImpl.findWithFilter(identification, fdesde, fhasta,
                name);

        if (medicalRecords.isEmpty()) {
            return ResponseEntity.ok(List.of());
        }

        return ResponseEntity.ok(medicalRecords);
    }

}
