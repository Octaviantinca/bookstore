package com.example.bookstore.configurations;

import com.example.bookstore.models.Author;
import com.example.bookstore.models.Book;
import com.example.bookstore.models.Library;
import com.example.bookstore.repositories.AuthorRepository;
import com.example.bookstore.repositories.LibraryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Configuration
public class ModelMapperConfiguration {

    @Bean
    CommandLineRunner commandLineRunner(AuthorRepository authorRepository, LibraryRepository libraryRepository) {
        return args -> {
            Set<Author> authors = new HashSet<>();

            Library library = new Library();
            library.setLibraryId(1);
            library.setName("Carturesti");
            library.setAddress("I.L.Caragiale 55");
            libraryRepository.save(library);


            Book b = new Book(
                    1,
                    "Hygge",
                    "literature",
                    authors,
                    library
            );

            Set<Book> books = new HashSet<>();
            books.add(b);

            Author Louisa = new Author(
                    1,
                    "Louisa",
                    "Brits",
                    books
            );

            Author Carl = new Author(
                    2,
                    "Carl",
                    "Marx",
                    books
            );

            authorRepository.saveAll(
                    List.of(Louisa, Carl)
            );
        };

    }

}
