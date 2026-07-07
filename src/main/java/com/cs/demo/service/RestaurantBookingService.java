package com.cs.demo.service;


import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.cs.demo.entity.RestaurantBooking;
import com.cs.demo.entity.RestaurantTable;
import com.cs.demo.repo.RestaurantBookingRepository;
import com.cs.demo.repo.RestaurantTableRepository;

@Service
public class RestaurantBookingService {

    private final RestaurantBookingRepository bookingRepo;
    private final RestaurantTableRepository tableRepo;

    public RestaurantBookingService(
            RestaurantBookingRepository bookingRepo,
            RestaurantTableRepository tableRepo) {

        this.bookingRepo = bookingRepo;
        this.tableRepo = tableRepo;
    }

    // ==========================
    // Save Booking
    // ==========================

    public RestaurantBooking save(RestaurantBooking booking) {

    	RestaurantTable table = tableRepo.findById(
    	        booking.getRestaurantTable().getTableId()
    	).orElseThrow(() -> new RuntimeException("Table Not Found"));

    	table.setTableStatus("RESERVED");

    	tableRepo.save(table);

    	booking.setRestaurantTable(table);
    	  booking.setBookingStatus("BOOKED");

          return bookingRepo.save(booking);
      }
    
    // ==========================
    // Get All
    // ==========================

    public List<RestaurantBooking> getAll() {

        return bookingRepo.findAll();

    }

    // ==========================
    // Get By Id
    // ==========================

    public RestaurantBooking getById(UUID id) {

        return bookingRepo.findById(id)

                .orElse(null);

    }

    // ==========================
    // Delete Booking
    // ==========================

    public void delete(UUID id) {

        RestaurantBooking booking =
                bookingRepo.findById(id)
                .orElseThrow();

        RestaurantTable table =
                booking.getRestaurantTable();

        table.setTableStatus("AVAILABLE");

        tableRepo.save(table);

        bookingRepo.deleteById(id);

    }

}