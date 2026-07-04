package com.cs.demo.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.*;

import com.cs.demo.entity.Customer;
import com.cs.demo.service.CustomerService;

@RestController
@RequestMapping("/customers")
@CrossOrigin("*")
public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @PostMapping
    public Customer save(@RequestBody Customer customer) {

        System.out.println(customer.getFirstName());

        return service.save(customer);
    }

    @GetMapping
    public List<Customer> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Customer getById(@PathVariable("id") UUID id) {
        return service.getById(id);
    }

   
    @PutMapping("/{id}")
    public Customer update(@PathVariable("id") UUID id,
                           @RequestBody Customer customer) {

        return service.update(id, customer);
    }
    
    


    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") UUID id) {
        service.delete(id);
        return "Deleted";
    }
}