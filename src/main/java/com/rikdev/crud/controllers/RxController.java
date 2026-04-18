package com.rikdev.crud.controllers;

import com.rikdev.crud.entities.Rx;
import com.rikdev.crud.services.RxServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rx")

public class RxController {
    private static final Logger logger = LoggerFactory.getLogger(RxController.class);

    @Autowired
    RxServiceImpl rxServiceImpl;

    @PostMapping            //GUARDAR LIBRO
    public ResponseEntity<Rx> saveRx(@RequestBody Rx rx){
        try {
            logger.info("Entidad que se va a almacenar: {}", rx);
            Rx saveRx = rxServiceImpl.saveRx(rx);
            logger.info("Se se ha guardado la rx en la base de datos: {}", saveRx);
            return new ResponseEntity<>(saveRx, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error inesperado: {}", e.getMessage(), e);
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping            //ACTUALIZAR LIBRO
    public ResponseEntity<Rx> updateRx(@RequestBody Rx rx){
        try {
            if(rx.getId_rx() != null && rxServiceImpl.getRxById(rx.getId_rx()).isPresent()){
                logger.info("Actualizando la rx con ID: {}", rx.getId_rx());
                Rx updatedRx = rxServiceImpl.updateRx(rx);
                return new ResponseEntity<>(updatedRx, HttpStatus.OK);
            } else {
                logger.warn("No se puede actualizar. La rx con ID: {} no existe.", rx.getId_rx());
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping            //OBTENER TODOS LOS LIBROS
    public ResponseEntity<List<Rx>> getAllRxs(){
        return new ResponseEntity<>(rxServiceImpl.getRx(),HttpStatus.OK);
    }

    @GetMapping("/{id}")            //OBTENER UN DISPOSITIVO POR ID
    /*public ResponseEntity<Rx> getRxById(@PathVariable Long id){*/
    public ResponseEntity<List<Rx>> getRxById(@PathVariable Long id) {
        Optional<Rx> rx = rxServiceImpl.getRxById(id);
        /*return rx.map(value ->new ResponseEntity<>(value,HttpStatus.OK)).orElseGet(() ->
                new ResponseEntity<>(HttpStatus.NOT_FOUND));*/

        // Si el dispositivo existe, se devuelve como un arreglo
        if (rx.isPresent()) {
            return new ResponseEntity<>(List.of(rx.get()), HttpStatus.OK);
        }

        // Si no existe, se devuelve un arreglo vacío
        return new ResponseEntity<>(List.of(), HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")            //ELIMINAR UN LIBRO POR ID
    public ResponseEntity<Rx> deleteRx(@PathVariable Long id){
        Optional <Rx> rx = rxServiceImpl.getRxById(id);
        if(rx.isPresent()){
            rxServiceImpl.deleteRx(rx.get().getId_rx());
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/search") //BUSQUEDA DE LOS DISPOSITIVOS POR UNA PALABRA CLAVE
    public ResponseEntity<List<Rx>> getRxKeyWord(@RequestParam  String keyword) {
        List<Rx> rxs = rxServiceImpl.searchByDescription(keyword);
        /*return rx.map(value ->new ResponseEntity<>(value,HttpStatus.OK)).orElseGet(() ->
                new ResponseEntity<>(HttpStatus.NOT_FOUND));*/

        // Si el dispositivo existe, se devuelve como un arreglo
        if (!rxs.isEmpty()) {
            return new ResponseEntity<>(rxs, HttpStatus.OK);
        }

        // Si no existe, se devuelve un arreglo vacío
        return new ResponseEntity<>(List.of(), HttpStatus.NOT_FOUND);
    }
}
