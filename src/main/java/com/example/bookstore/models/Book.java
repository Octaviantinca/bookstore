package com.example.bookstore.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table( name ="book")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    @SequenceGenerator(
            name = "book_sequence",
            sequenceName = "book_sequence",
            allocationSize = 1
    )
    @GeneratedValue( strategy = GenerationType.SEQUENCE,
            generator = "book_sequence"
    )
    @Column(name = "book_id")
    private Integer bookId;
    @Column(name = "title")
    private String title;
    @Column(name = "genre")
    private String genre;

    @JsonIgnore
    @ManyToMany(mappedBy = "books", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Author> authors;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(
            name = "library_id"
    )
    private Library library;
}
