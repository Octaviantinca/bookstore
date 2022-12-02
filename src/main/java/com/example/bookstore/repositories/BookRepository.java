package com.example.bookstore.repositories;

import com.example.bookstore.models.Author;
import com.example.bookstore.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    Optional<Book> findBookByTitle(String title);


    Optional<Object> findByTitle(Integer bookId);
}
