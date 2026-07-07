package com.cs.demo.service;


import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.cs.demo.entity.RestaurantTable;
import com.cs.demo.repo.RestaurantTableRepository;

@Service
public class RestaurantTableService {

    private final RestaurantTableRepository repo;

    public RestaurantTableService(RestaurantTableRepository repo) {
        this.repo = repo;
    }

    // Save Restaurant Table
    public RestaurantTable save(RestaurantTable table) {

        if (table.getTableStatus() == null ||
            table.getTableStatus().isBlank()) {

            table.setTableStatus("AVAILABLE");
        }

        return repo.save(table);
    }

    // Get All Restaurant Tables
    public List<RestaurantTable> getAll() {

        return repo.findAll();
    }

    // Get Restaurant Table By Id
    public RestaurantTable getById(UUID id) {

        return repo.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Restaurant Table Not Found"));
    }

    // Update Restaurant Table
    public RestaurantTable update(
            UUID id,
            RestaurantTable table) {

        RestaurantTable existing = getById(id);

        existing.setTableNumber(table.getTableNumber());
        existing.setCapacity(table.getCapacity());
        existing.setTableStatus(table.getTableStatus());

        return repo.save(existing);
    }

    // Delete Restaurant Table
    public void delete(UUID id) {

        RestaurantTable table = getById(id);

        repo.delete(table);
    }

}