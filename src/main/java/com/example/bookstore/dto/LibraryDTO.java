package com.example.bookstore.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
@Getter
@Setter
public class LibraryDTO {
    private Integer libraryId;
    private String name;
    private String address;
}
