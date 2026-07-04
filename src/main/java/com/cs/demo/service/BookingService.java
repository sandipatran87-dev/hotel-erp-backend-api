package com.cs.demo.service;


import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.cs.demo.entity.Booking;
import com.cs.demo.entity.Room;
import com.cs.demo.enums.RoomStatus;
import com.cs.demo.repo.BookingRepository;
import com.cs.demo.repo.RoomRepository;

@Service
public class BookingService {

    private final BookingRepository bookingRepo;
    private final RoomRepository roomRepo;

    public BookingService(BookingRepository bookingRepo,
                          RoomRepository roomRepo) {

        this.bookingRepo = bookingRepo;
        this.roomRepo = roomRepo;
    }

    // Save Booking
    public Booking save(Booking booking) {

        Room room = roomRepo.findById(
                booking.getRoom().getRoomId())
                .orElseThrow(() ->
                        new RuntimeException("Room Not Found"));

        // Room already booked?
        if (room.getRoomStatus() == RoomStatus.BOOKED) {
            throw new RuntimeException("Room Already Booked");
        }

        // Calculate Total Days
        long days = ChronoUnit.DAYS.between(
                booking.getCheckInDate(),
                booking.getCheckOutDate());

        if (days <= 0) {
            throw new RuntimeException(
                    "Invalid Check-In / Check-Out Date");
        }

        booking.setTotalDays((int) days);

        // Calculate Amount
        BigDecimal amount = room.getRoomType()
                .getBasePrice()
                .multiply(BigDecimal.valueOf(days));

        booking.setTotalAmount(amount);

        // Update Room Status
        room.setRoomStatus(RoomStatus.BOOKED);
        roomRepo.save(room);

        return bookingRepo.save(booking);
    }

    // Get All Bookings
    public List<Booking> getAll() {
        return bookingRepo.findAll();
    }

    // Get Booking By Id
    public Booking getById(UUID id) {

        return bookingRepo.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Booking Not Found"));
    }

    // Delete Booking
    public void delete(UUID id) {

        Booking booking = getById(id);

        Room room = booking.getRoom();

        room.setRoomStatus(RoomStatus.AVAILABLE);

        roomRepo.save(room);

        bookingRepo.delete(booking);
    }
    
 // Update Booking
    public Booking update(UUID id, Booking booking) {

        Booking existing = getById(id);

        existing.setCustomer(booking.getCustomer());
        existing.setRoom(booking.getRoom());
        existing.setCheckInDate(booking.getCheckInDate());
        existing.setCheckOutDate(booking.getCheckOutDate());
        existing.setBookingStatus(booking.getBookingStatus());

        return bookingRepo.save(existing);
    }
}