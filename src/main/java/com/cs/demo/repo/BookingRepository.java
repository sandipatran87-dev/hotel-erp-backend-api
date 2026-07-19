package com.cs.demo.repo;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cs.demo.entity.Booking;
import com.cs.demo.entity.Customer;
import com.cs.demo.enums.BookingStatus;

public interface BookingRepository extends JpaRepository<Booking, UUID> {

    Long countByBookingStatus(BookingStatus bookingStatus);
    
    long countByCustomer(Customer customer);

}
