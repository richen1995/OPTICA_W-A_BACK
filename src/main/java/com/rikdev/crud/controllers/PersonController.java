package com.rikdev.crud.controllers;


import com.rikdev.crud.entities.Person;
import com.rikdev.crud.services.PersonServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/person")

public class PersonController {

    // Crear una instancia de Logger
    private static final Logger logger = LoggerFactory.getLogger(PersonController.class);

    @Autowired
    PersonServiceImpl personServiceImpl;

    @PostMapping            //GUARDAR LIBRO
    public ResponseEntity<Person> savePerson(@RequestBody Person person){
        try {
            Person savePerson = personServiceImpl.savePerson(person);
            logger.info("Se se ha guardado la persona en la base de datos");
            return new ResponseEntity<>(savePerson, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error inesperado: {}", e.getMessage(), e);
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping            //ACTUALIZAR LIBRO
    public ResponseEntity<Person> updatePerson(@RequestBody Person person){
        try {
            if(person.getId_person() != null && personServiceImpl.getPersonById(person.getId_person()).isPresent()){
                logger.info("Actualizando la persona con ID: {}", person.getId_person());
                Person updatedPerson = personServiceImpl.updatePerson(person);
                return new ResponseEntity<>(updatedPerson, HttpStatus.OK);
            } else {
                logger.warn("No se puede actualizar. La persona con ID: {} no existe.", person.getId_person());
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping            //OBTENER TODOS LOS LIBROS
    public ResponseEntity<List<Person>> getAllPersons(){
        return new ResponseEntity<>(personServiceImpl.getPersons(),HttpStatus.OK);
    }

    @GetMapping("/{id}")            //OBTENER UN DISPOSITIVO POR ID
    /*public ResponseEntity<Person> getPersonById(@PathVariable Long id){*/
    public ResponseEntity<List<Person>> getPersonById(@PathVariable Long id) {
        Optional<Person> person = personServiceImpl.getPersonById(id);
        /*return person.map(value ->new ResponseEntity<>(value,HttpStatus.OK)).orElseGet(() ->
                new ResponseEntity<>(HttpStatus.NOT_FOUND));*/

        // Si el dispositivo existe, se devuelve como un arreglo
        if (person.isPresent()) {
            return new ResponseEntity<>(List.of(person.get()), HttpStatus.OK);
        }

        // Si no existe, se devuelve un arreglo vacío
        return new ResponseEntity<>(List.of(), HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")            //ELIMINAR UN LIBRO POR ID
    public ResponseEntity<Person> deletePerson(@PathVariable Long id){
        Optional <Person> person = personServiceImpl.getPersonById(id);
        if(person.isPresent()){
            personServiceImpl.deletePerson(person.get().getId_person());
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/identification/{id}") // OBTENER UN DISPOSITIVO POR IDENTIFICACIÓN
    public ResponseEntity<List<Person>> getPersonByIdentification(@PathVariable String id) {
        Optional<Person> person = personServiceImpl.getPersonByIdentification(id);

        if (person.isPresent()) {
            return new ResponseEntity<>(List.of(person.get()), HttpStatus.OK);
        }

        return new ResponseEntity<>(List.of(), HttpStatus.NOT_FOUND);
    }

    @GetMapping("/search") //BUSQUEDA DE LOS DISPOSITIVOS POR UNA PALABRA CLAVE
    public ResponseEntity<List<Person>> getPersonKeyWord(@RequestParam  String keyword) {
        List<Person> persons = personServiceImpl.searchByDescription(keyword);
        /*return person.map(value ->new ResponseEntity<>(value,HttpStatus.OK)).orElseGet(() ->
                new ResponseEntity<>(HttpStatus.NOT_FOUND));*/

        // Si el dispositivo existe, se devuelve como un arreglo
        if (!persons.isEmpty()) {
            return new ResponseEntity<>(persons, HttpStatus.OK);
        }

        // Si no existe, se devuelve un arreglo vacío
        return new ResponseEntity<>(List.of(), HttpStatus.NOT_FOUND);
    }

    /*@GetMapping("/search/{id}/{finicial}/{ffinal}")            //OBTENER UN DISPOSITIVO POR ID
    public ResponseEntity<List<Person>> getPersonMedicalHistory(@PathVariable Long id) {
        Optional<Person> person = personServiceImpl.getPersonById(id);
        /*return person.map(value ->new ResponseEntity<>(value,HttpStatus.OK)).orElseGet(() ->
                new ResponseEntity<>(HttpStatus.NOT_FOUND));*/

        // Si el dispositivo existe, se devuelve como un arreglo
        /*if (person.isPresent()) {
            return new ResponseEntity<>(List.of(person.get()), HttpStatus.OK);
        }

        // Si no existe, se devuelve un arreglo vacío
        return new ResponseEntity<>(List.of(), HttpStatus.NOT_FOUND);
    }*/

}
