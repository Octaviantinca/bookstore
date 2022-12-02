package com.example.bookstore.controllers;

import com.example.bookstore.dto.LibraryDTO;
import com.example.bookstore.models.Author;
import com.example.bookstore.models.Library;
import com.example.bookstore.services.AuthorService;
import com.example.bookstore.services.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path ="api/v1/library")
public class LibraryController {

    private final LibraryService libraryService;

    @Autowired
    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping
    public List<LibraryDTO> getLibraries() {
        return libraryService.getLibraries();
    }

    @PostMapping
    public void registerNewLibrary(@RequestBody Library library) {
        libraryService.addNewLibrary(library);
    }

    @DeleteMapping( path ="{libraryId}")
    public void deleteLibrary(@PathVariable("libraryId") Integer libraryId) {
        libraryService.deleteLibrary(libraryId);
    }

    @PutMapping(path ="{libraryId}")
    public void updateLibrary(
            @PathVariable("libraryId") Integer libraryId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String address) {
        libraryService.updateLibrary(libraryId, name, address);
    }
}
