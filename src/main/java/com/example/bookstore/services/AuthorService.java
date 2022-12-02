package com.example.bookstore.services;

import com.example.bookstore.dto.AuthorDTO;
import com.example.bookstore.models.Author;
import com.example.bookstore.models.Book;
import com.example.bookstore.repositories.AuthorRepository;
import com.example.bookstore.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<AuthorDTO> getAuthors() {
        return authorRepository.findAll().stream().map(this::convertEntityToDTO).collect(Collectors.toList());
    }

    public Author getOne(Integer authorId) {
        return authorRepository.findById(authorId).get();
    }

    private AuthorDTO convertEntityToDTO(Author author) {
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setAuthorId(author.getAuthorId());
        authorDTO.setFirstName(author.getFirstName());
        authorDTO.setLastName(author.getLastName());
        authorDTO.setBooks(author.getBooks());
        return authorDTO;
    }

    public void addNewAuthor(Author author) {
        Optional<Author> authorOptional = authorRepository
                .findAuthorByLastName(author.getLastName());
        if (authorOptional.isPresent()) {
            throw new IllegalStateException("name taken");
        }
        authorRepository.save(author);
    }

    public void deleteAuthor(Integer authorId) {

        boolean exists = authorRepository.existsById(authorId);
        if (!exists) {
            throw new IllegalStateException("author with id " + authorId + "does not exists");
        }
        authorRepository.deleteById(authorId);
    }

    @Transactional
    public Author updateAuthor(Integer authorId, String firstName, String lastName, Set<Book> books) {
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new IllegalStateException(
                        "author with id " + authorId + "does not exist"));

        if (firstName != null &&
                firstName.length() > 0 &&
                !Objects.equals(author.getFirstName(), firstName)) {
            author.setFirstName(firstName);
        }

        if (lastName != null &&
                lastName.length() > 0 &&
                !Objects.equals(author.getLastName(), lastName)) {
            author.setLastName(lastName);
        }
        if (!books.isEmpty())
        {
            author.setBooks(books);
        }
        return author;
    }

}
