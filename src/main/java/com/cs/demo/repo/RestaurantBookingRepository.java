package com.cs.demo.repo;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cs.demo.entity.RestaurantBooking;

public interface RestaurantBookingRepository extends JpaRepository<RestaurantBooking, UUID> {

    // All bookings of a table
    List<RestaurantBooking> findByRestaurantTable_TableId(UUID tableId);

    // All bookings of a customer
    List<RestaurantBooking> findByCustomer_CustomerId(UUID customerId);

    // Duplicate booking check
    boolean existsByRestaurantTable_TableIdAndBookingDateAndBookingTime(
            UUID tableId,
            LocalDate bookingDate,
            LocalTime bookingTime
    );

}
