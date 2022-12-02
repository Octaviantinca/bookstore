package com.example.bookstore.controllers;

import com.example.bookstore.dto.AuthorDTO;
import com.example.bookstore.models.Author;
import com.example.bookstore.models.Book;
import com.example.bookstore.services.AuthorService;
import com.example.bookstore.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(path ="api/v1/author")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;
    private final BookService bookService;

    @GetMapping
    public List<AuthorDTO> getAuthors() {
        return authorService.getAuthors();
    }

    @PostMapping
    public void registerNewAuthor(@RequestBody Author author) {
        authorService.addNewAuthor(author);
    }

    @DeleteMapping( path ="{authorId}")
    public void deleteAuthor(@PathVariable("authorId") Integer authorId) {
        authorService.deleteAuthor(authorId);
    }

    @PutMapping(path ="{authorId}")
    public void updateAuthorName(
            @PathVariable("authorId") Integer authorId,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName) {
        authorService.updateAuthor(authorId, firstName, lastName, Set.of());
    }

    @PutMapping(path ="/{authorId}/book/{bookId}")
    public Author booksAuthor(
            @PathVariable Integer authorId,
            @PathVariable Integer bookId
    ) {
        Book book = bookService.getOne(bookId);
        return authorService.updateAuthor(authorId, null, null, Set.of(book));
    }


}
