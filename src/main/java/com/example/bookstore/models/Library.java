package com.example.bookstore.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table( name ="library")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Library {
    @Id
    @SequenceGenerator(
            name = "library_sequence",
            sequenceName = "library_sequence",
            allocationSize = 1
    )
    @GeneratedValue( strategy = GenerationType.SEQUENCE,
            generator = "library_sequence"
    )
    @Column(name = "library_id")
    private Integer libraryId;
    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;

    @JsonIgnore
    @OneToMany(mappedBy = "library")
    private Set<Book> books;

    @JsonIgnore
    @OneToMany(mappedBy = "library")
    private Set<Customer> customers;
}
