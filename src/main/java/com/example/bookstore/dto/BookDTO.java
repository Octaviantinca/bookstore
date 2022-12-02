package com.example.bookstore.dto;

import com.example.bookstore.models.Author;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.util.Set;

@Getter
@Setter
public class BookDTO {
    private Integer bookId;
    private String title;
    private String genre;
    private Set<Author> authors;
}
