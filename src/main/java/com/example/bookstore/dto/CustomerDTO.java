package com.example.bookstore.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
public class CustomerDTO {

    private Integer customerId;
    private String firstName;
    private String lastName;
}
