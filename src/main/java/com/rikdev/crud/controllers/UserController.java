package com.rikdev.crud.controllers;

import com.rikdev.crud.entities.User;

import com.rikdev.crud.services.UserServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    // Crear una instancia de Logger
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserServiceImpl userServiceImpl;

    @PostMapping // GUARDAR USUARIO
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        try {
            User saveUser = userServiceImpl.saveUser(user);
            logger.info("Se se ha guardado la usera en la base de datos");
            return new ResponseEntity<>(saveUser, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error inesperado: {}", e.getMessage(), e);
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping // ACTUALIZAR USUARIO
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        try {
            if (user.getIdUser() != null && userServiceImpl.getUserById(user.getIdUser()).isPresent()) {
                logger.info("Actualizando la usera con ID: {}", user.getIdUser());
                User updatedUser = userServiceImpl.updateUser(user);
                return new ResponseEntity<>(updatedUser, HttpStatus.OK);
            } else {
                logger.warn("No se puede actualizar. La usera con ID: {} no existe.", user.getIdUser());
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping // OBTENER TODOS LOS USUARIOS
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userServiceImpl.getUsers(), HttpStatus.OK);
    }

    @GetMapping("/{id}") // OBTENER UN DISPOSITIVO POR ID

    public ResponseEntity<List<User>> getUserById(@PathVariable Long id) {
        Optional<User> user = userServiceImpl.getUserById(id);

        // Si el dispositivo existe, se devuelve como un arreglo
        if (user.isPresent()) {
            return new ResponseEntity<>(List.of(user.get()), HttpStatus.OK);
        }

        // Si no existe, se devuelve un arreglo vacío
        return new ResponseEntity<>(List.of(), HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}") // ELIMINAR UN USUARIO POR ID
    public ResponseEntity<User> deleteUser(@PathVariable Long id) {
        Optional<User> user = userServiceImpl.getUserById(id);
        if (user.isPresent()) {
            userServiceImpl.deleteUser(user.get().getIdUser());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/search") // BUSQUEDA DE LOS DISPOSITIVOS POR UNA PALABRA CLAVE
    public ResponseEntity<List<User>> getUserKeyWord(@RequestParam String keyword) {
        List<User> users = userServiceImpl.searchByDescription(keyword);

        // Si el dispositivo existe, se devuelve como un arreglo
        if (!users.isEmpty()) {
            return new ResponseEntity<>(users, HttpStatus.OK);
        }

        // Si no existe, se devuelve un arreglo vacío
        return new ResponseEntity<>(List.of(), HttpStatus.NOT_FOUND);
    }
}
