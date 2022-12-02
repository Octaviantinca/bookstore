package com.example.bookstore.services;

import com.example.bookstore.dto.BookDTO;
import com.example.bookstore.models.Author;
import com.example.bookstore.models.Book;
import com.example.bookstore.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book getOne(Integer bookId) {
        return bookRepository.findById(bookId).get();
    }


    public List<BookDTO> getBooks() {
        return bookRepository.findAll().stream().map(this::convertEntityToDTO).collect(Collectors.toList());
}

    private BookDTO convertEntityToDTO(Book book) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setBookId(book.getBookId());
        bookDTO.setTitle(book.getTitle());
        bookDTO.setGenre(book.getGenre());
        bookDTO.setAuthors(book.getAuthors());
        return bookDTO;
    }

    public void addNewBook(Book book) {
        Optional<Book> bookOptional = bookRepository
                .findBookByTitle(book.getTitle());
        if (bookOptional.isPresent()) {
            throw new IllegalStateException("title taken");
        }
        bookRepository.save(book);
    }

    @Transactional
    public void updateBook(Integer bookId, String title, String genre) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalStateException(
                        "book with id " + bookId + "does not exist"));

        if (title != null &&
                title.length() > 0 &&
                !Objects.equals(book.getTitle(), title)) {
            book.setTitle(title);
        }

    }

    public void deleteBook(Integer bookId) {
        boolean exists = bookRepository.existsById(bookId);
        if (!exists) {
            throw new IllegalStateException("book with id " + bookId + "does not exists");
        }
        bookRepository.deleteById(bookId);
    }
}
