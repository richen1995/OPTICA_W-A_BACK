package com.rikdev.crud.services;

import com.rikdev.crud.entities.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    Book saveBook(Book book);
    Book updateBook(Book book);
    List<Book> getBooks();                  //obtener todos los libros
    Optional<Book> getBookById(Long id);
    void deleteBook(Long id);
}
