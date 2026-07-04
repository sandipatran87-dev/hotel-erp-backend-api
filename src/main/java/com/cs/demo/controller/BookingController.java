package com.cs.demo.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.*;

import com.cs.demo.entity.Booking;
import com.cs.demo.service.BookingService;

@RestController
@RequestMapping("/bookings")
@CrossOrigin("*")
public class BookingController {

    private final BookingService service;

    public BookingController(BookingService service) {
        this.service = service;
    }

    // Save Booking
    @PostMapping
    public Booking save(@RequestBody Booking booking) {
        return service.save(booking);
    }

    // Get All Bookings
    @GetMapping
    public List<Booking> getAll() {
        return service.getAll();
    }

    // Get Booking By Id
    @GetMapping("/{id}")
    public Booking getById(@PathVariable("id") UUID id) {

        return service.getById(id);

    }
    
 // Update Booking
    @PutMapping("/{id}")
    public Booking update(

            @PathVariable("id") UUID id,

            @RequestBody Booking booking) {

        return service.update(id, booking);

    }

    // Delete Booking
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") UUID id) {

        service.delete(id);

        return "Booking Deleted Successfully";
    }

}