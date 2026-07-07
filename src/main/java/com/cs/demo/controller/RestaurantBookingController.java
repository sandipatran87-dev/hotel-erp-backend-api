package com.cs.demo.controller;


import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cs.demo.entity.RestaurantBooking;
import com.cs.demo.service.RestaurantBookingService;

@RestController
@RequestMapping("/restaurant-bookings")
@CrossOrigin(origins = "*")
public class RestaurantBookingController {

    private final RestaurantBookingService service;

    public RestaurantBookingController(
            RestaurantBookingService service) {

        this.service = service;
    }

    // ==========================
    // Save Booking
    // ==========================

    @PostMapping
    public ResponseEntity<RestaurantBooking> save(
            @RequestBody RestaurantBooking booking) {

        return ResponseEntity.ok(
                service.save(booking));

    }

    // ==========================
    // Get All Bookings
    // ==========================

    @GetMapping
    public ResponseEntity<List<RestaurantBooking>> getAll() {

        return ResponseEntity.ok(
                service.getAll());

    }

    // ==========================
    // Get Booking By Id
    // ==========================

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantBooking> getById(
            @PathVariable("id") UUID id) {

        return ResponseEntity.ok(
                service.getById(id));

    }

    // ==========================
    // Delete Booking
    // ==========================

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(
            @PathVariable("id") UUID id) {

        service.delete(id);

        return ResponseEntity.ok(
                "Restaurant Booking Deleted Successfully");

    }

}