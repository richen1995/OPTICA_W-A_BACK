package com.rikdev.crud.controllers;

import com.rikdev.crud.entities.Book;
import com.rikdev.crud.services.BookServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/book")

public class BookController {

    // Crear una instancia de Logger
    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    BookServiceImpl bookServiceImpl;

    @PostMapping            //GUARDAR LIBRO
    public ResponseEntity<Book> saveBook(@RequestBody Book book){
        try {
            Book saveBook = bookServiceImpl.saveBook(book);
            logger.info("Se se ha guardado el libro");
            System.out.println("Hola, este es un mensaje en la consola.");
            return new ResponseEntity<>(saveBook, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error inesperado: {}", e.getMessage(), e);
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping            //ACTUALIZAR LIBRO
    public ResponseEntity<Book> updateBook(@RequestBody Book book){
        try {
            Book saveBook = bookServiceImpl.updateBook(book);
            return new ResponseEntity<>(saveBook, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping            //OBTENER TODOS LOS LIBROS
    public ResponseEntity<List<Book>> getAllBooks(){
            return new ResponseEntity<>(bookServiceImpl.getBooks(),HttpStatus.OK);
    }

    @GetMapping("/{id}")            //OBTENER UN LIBRO POR ID
    public ResponseEntity<Book> getBookById(@PathVariable Long id){
        Optional <Book> book = bookServiceImpl.getBookById(id);
        return book.map(value ->new ResponseEntity<>(value,HttpStatus.OK)).orElseGet(() ->
                    new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")            //ELIMINAR UN LIBRO POR ID
    public ResponseEntity<Book> deleteBook(@PathVariable Long id){
        Optional <Book> book = bookServiceImpl.getBookById(id);
        if(book.isPresent()){
            bookServiceImpl.deleteBook(book.get().getId());
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
