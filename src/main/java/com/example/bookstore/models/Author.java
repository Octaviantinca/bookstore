package com.example.bookstore.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table( name ="author")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Author {

    @Id
    @SequenceGenerator(
            name = "author_sequence",
            sequenceName = "author_sequence",
            allocationSize = 1
    )
    @GeneratedValue( strategy = GenerationType.SEQUENCE,
            generator = "author_sequence"
    )
    @Column(name ="author_id")
    private Integer authorId;
    @Column(name ="first_name")
    private String firstName;
    @Column(name ="last_name")
    private String lastName;

    @JsonIgnore
    @ManyToMany( fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name ="books_authors",
            joinColumns = @JoinColumn(name = "authorId"),
            inverseJoinColumns = @JoinColumn(name = "bookId")
    )
    private Set<Book> books;

}
