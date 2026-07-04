package com.cs.demo.service;


import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.cs.demo.entity.Customer;
import com.cs.demo.repo.CustomerRepository;

@Service
public class CustomerService {

    private final CustomerRepository repo;

    public CustomerService(CustomerRepository repo) {
        this.repo = repo;
    }

    public Customer save(Customer customer) {
        return repo.save(customer);
    }

    public List<Customer> getAll() {
        return repo.findAll();
    }

    public Customer getById(UUID id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer Not Found"));
    }

    public Customer update(UUID id, Customer customer) {

        Customer old = getById(id);

        old.setFirstName(customer.getFirstName());
        old.setLastName(customer.getLastName());
        old.setGender(customer.getGender());
        old.setMobile(customer.getMobile());
        old.setEmail(customer.getEmail());
        old.setAadharNo(customer.getAadharNo());
        old.setAddress(customer.getAddress());
        old.setCity(customer.getCity());
        old.setState(customer.getState());
        old.setCountry(customer.getCountry());

        return repo.save(old);
    }
    
    
    public void delete(UUID id) {
        repo.deleteById(id);
    }
}