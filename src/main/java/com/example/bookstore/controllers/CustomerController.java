package com.example.bookstore.controllers;

import com.example.bookstore.dto.CustomerDTO;
import com.example.bookstore.models.Author;
import com.example.bookstore.models.Customer;
import com.example.bookstore.services.AuthorService;
import com.example.bookstore.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path ="api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<CustomerDTO> getCustomers() {
        return customerService.getCustomers();
    }

    @PostMapping
    public void registerNewCustomer(@RequestBody Customer customer) {
        customerService.addNewCustomer(customer);
    }

    @DeleteMapping( path ="{customerId}")
    public void deleteCustomer(@PathVariable("customerId") Integer customerId) {
        customerService.deleteCustomer(customerId);
    }

    @PutMapping(path ="{customerId}")
    public void updateCustomer(
            @PathVariable("customerId") Integer customerId,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName) {
        customerService.updateCustomer(customerId, firstName, lastName);
    }


}
