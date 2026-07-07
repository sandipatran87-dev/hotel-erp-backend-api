package com.cs.demo.controller;


import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.*;

import com.cs.demo.entity.RestaurantTable;
import com.cs.demo.service.RestaurantTableService;

@RestController
@RequestMapping("/restaurant-tables")
@CrossOrigin("*")
public class RestaurantTableController {

    private final RestaurantTableService service;

    public RestaurantTableController(
            RestaurantTableService service) {

        this.service = service;
    }

    // Save Restaurant Table
   
    
    @PostMapping
    public RestaurantTable save(
            @RequestBody RestaurantTable table) {

        System.out.println("===============");
        System.out.println("Table Number : " + table.getTableNumber());
        System.out.println("Capacity     : " + table.getCapacity());
        System.out.println("Status       : " + table.getTableStatus());
        System.out.println("===============");

        return service.save(table);
    }

    // Get All Restaurant Tables
    @GetMapping
    public List<RestaurantTable> getAll() {

        return service.getAll();
    }

    // Get Restaurant Table By Id
    @GetMapping("/{id}")
    public RestaurantTable getById(
            @PathVariable("id") UUID id) {

        return service.getById(id);
    }

    // Update Restaurant Table
    @PutMapping("/{id}")
    public RestaurantTable update(
            @PathVariable("id") UUID id,
            @RequestBody RestaurantTable table) {

        return service.update(id, table);
    }

    // Delete Restaurant Table
    @DeleteMapping("/{id}")
    public String delete(
            @PathVariable("id") UUID id) {

        service.delete(id);

        return "Restaurant Table Deleted Successfully";
    }

}