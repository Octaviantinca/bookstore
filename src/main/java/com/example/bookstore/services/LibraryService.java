package com.example.bookstore.services;

import com.example.bookstore.dto.LibraryDTO;
import com.example.bookstore.models.Author;
import com.example.bookstore.models.Library;
import com.example.bookstore.repositories.AuthorRepository;
import com.example.bookstore.repositories.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LibraryService {

    private final LibraryRepository libraryRepository;

    @Autowired
    public LibraryService(LibraryRepository libraryRepository) {
        this.libraryRepository = libraryRepository;
    }

    public List<LibraryDTO> getLibraries() {
        return libraryRepository.findAll().stream().map(this::convertEntityToDTO).collect(Collectors.toList());
    }

    private LibraryDTO convertEntityToDTO(Library library) {
        LibraryDTO libraryDTO = new LibraryDTO();
        libraryDTO.setLibraryId(library.getLibraryId());
        libraryDTO.setName(library.getName());
        libraryDTO.setAddress(library.getAddress());
        return libraryDTO;
    }

    public void addNewLibrary(Library library) {
        Optional<Library> libraryOptional = libraryRepository
                .findLibraryByName(library.getName());
        if (libraryOptional.isPresent()) {
            throw new IllegalStateException("name taken");
        }
        libraryRepository.save(library);
    }

    public void deleteLibrary(Integer libraryId) {
        boolean exists = libraryRepository.existsById(libraryId);
        if (!exists) {
            throw new IllegalStateException("library with id " + libraryId + "does not exists");
        }
        libraryRepository.deleteById(libraryId);
    }

    @Transactional
    public void updateLibrary(Integer libraryId, String name, String address) {
        Library library = libraryRepository.findById(libraryId)
                .orElseThrow(() -> new IllegalStateException(
                        "library with id " + libraryId + "does not exist"));

        if (name != null &&
                name.length() > 0 &&
                !Objects.equals(library.getName(), name)) {
            library.setName(name);
        }

        if (address != null &&
                address.length() > 0 &&
                !Objects.equals(library.getAddress(), address)) {
            library.setAddress(address);
        }
    }
}
