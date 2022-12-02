package com.example.bookstore.controllers;

import com.example.bookstore.dto.BookDTO;
import com.example.bookstore.models.Book;
import com.example.bookstore.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/book")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<BookDTO> getBooks() {
        return bookService.getBooks();
    }

    @PostMapping
    public void registerNewBook(@RequestBody Book book) {
        bookService.addNewBook(book);
    }

    @DeleteMapping( path ="{bookId}")
    public void deleteBook(@PathVariable("bookId") Integer bookId) {
        bookService.deleteBook(bookId);
    }

    @PutMapping(path ="{bookId}")
    public void updateBook(
            @PathVariable("bookId") Integer bookId,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String genre) {
        bookService.updateBook(bookId, title, genre);
    }

}
