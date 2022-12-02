package com.example.bookstore.dto;

import com.example.bookstore.models.Book;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.util.Set;

@Getter
@Setter
public class AuthorDTO {
    private Integer authorId;
    private String firstName;
    private String lastName;
    private Set<Book> books;
}
