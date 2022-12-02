package com.example.bookstore.repositories;

import com.example.bookstore.models.Library;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LibraryRepository extends JpaRepository<Library, Integer> {
    Optional<Library> findLibraryByName(String name);
}
