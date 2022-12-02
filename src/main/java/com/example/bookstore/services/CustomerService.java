package com.example.bookstore.services;

import com.example.bookstore.dto.CustomerDTO;
import com.example.bookstore.models.Author;
import com.example.bookstore.models.Customer;
import com.example.bookstore.repositories.AuthorRepository;
import com.example.bookstore.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<CustomerDTO> getCustomers() {
        return customerRepository.findAll().stream().map(this::convertEntityToDTO).collect(Collectors.toList());
    }

    private CustomerDTO convertEntityToDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomerId(customer.getCustomerId());
        customerDTO.setFirstName(customer.getFirstName());
        customerDTO.setLastName(customer.getLastName());
        return customerDTO;
    }

    public void addNewCustomer(Customer customer) {
        Optional<Customer> customerOptional = customerRepository
                .findCustomerByLastName(customer.getLastName());
        if (customerOptional.isPresent()) {
            throw new IllegalStateException("customer name taken");
        }
        customerRepository.save(customer);
    }

    public void deleteCustomer(Integer customerId) {
        boolean exists = customerRepository.existsById(customerId);
        if (!exists) {
            throw new IllegalStateException("customer with id " + customerId + "does not exists");
        }
        customerRepository.deleteById(customerId);
    }

    @Transactional
    public void updateCustomer(Integer customerId, String firstName, String lastName) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalStateException(
                        "customer with id " + customerId + "does not exist"));

        if (firstName != null &&
                firstName.length() > 0 &&
                !Objects.equals(customer.getFirstName(), firstName)) {
            customer.setFirstName(firstName);
        }

        if (lastName != null &&
                lastName.length() > 0 &&
                !Objects.equals(customer.getLastName(), lastName)) {
            customer.setLastName(lastName);
        }

    }
}
