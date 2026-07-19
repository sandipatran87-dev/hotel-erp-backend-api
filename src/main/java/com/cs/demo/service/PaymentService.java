package com.cs.demo.service;


import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.cs.demo.entity.Bill;
import com.cs.demo.entity.Booking;
import com.cs.demo.entity.Payment;
import com.cs.demo.entity.RestaurantBooking;
import com.cs.demo.entity.RestaurantTable;
import com.cs.demo.enums.BillStatus;
import com.cs.demo.enums.BillType;
import com.cs.demo.enums.BookingStatus;
import com.cs.demo.enums.PaymentStatus;
import com.cs.demo.enums.RoomStatus;
import com.cs.demo.repo.BillRepository;
import com.cs.demo.repo.BookingRepository;
import com.cs.demo.repo.PaymentRepository;
import com.cs.demo.repo.RestaurantBookingRepository;
import com.cs.demo.repo.RestaurantTableRepository;
import com.cs.demo.repo.RoomRepository;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepo;
    private final BillRepository billRepo;
    private final BookingRepository bookingRepo;
    private final RestaurantBookingRepository restaurantBookingRepo;
    private final RestaurantTableRepository tableRepo;
    private final RoomRepository roomRepo;

    public PaymentService(
            PaymentRepository paymentRepo,
            BillRepository billRepo,
            BookingRepository bookingRepo,
            RestaurantBookingRepository restaurantBookingRepo,
            RestaurantTableRepository tableRepo,
            RoomRepository roomRepo) {

        this.paymentRepo = paymentRepo;
        this.billRepo = billRepo;
        this.bookingRepo = bookingRepo;
        this.restaurantBookingRepo = restaurantBookingRepo;
        this.tableRepo = tableRepo;
        this.roomRepo = roomRepo;
    }

    // ==========================
    // Make Payment
    // ==========================

    public Payment makePayment(Payment payment) {

        Bill bill = billRepo.findById(
                payment.getBill().getBillId())
                .orElseThrow(() ->
                        new RuntimeException("Bill Not Found"));
        
        if (bill.getBillStatus() == BillStatus.PAID) {

            throw new RuntimeException("Bill Already Paid");

        }
        

        // Check Duplicate Payment
        if (paymentRepo.findByBill(bill).isPresent()) {

            throw new RuntimeException("Payment Already Completed");

        }

        // Set Bill
        payment.setBill(bill);

        // Amount
        payment.setAmount(bill.getGrandTotal());

        // Payment Status
        payment.setPaymentStatus(PaymentStatus.PAID);

        // Transaction Id
        payment.setTransactionId(
                UUID.randomUUID().toString());

        // Save Payment
        Payment savedPayment = paymentRepo.save(payment);

        // Update Bill Status
        bill.setBillStatus(BillStatus.PAID);

        billRepo.save(bill);

        // ==========================
        // ROOM PAYMENT
        // ==========================

        if (bill.getBillType() == BillType.ROOM) {

            Booking booking = bill.getRoomBooking();

            booking.setBookingStatus(BookingStatus.CHECKED_OUT);

            bookingRepo.save(booking);

            booking.getRoom().setRoomStatus(RoomStatus.AVAILABLE);

            roomRepo.save(booking.getRoom());

        }

        // ==========================
        // RESTAURANT PAYMENT
        // ==========================

        else if (bill.getBillType() == BillType.RESTAURANT) {

            RestaurantBooking booking =
                    bill.getFoodOrder().getRestaurantBooking();

            booking.setBookingStatus("COMPLETED");

            restaurantBookingRepo.save(booking);

            RestaurantTable table =
                    booking.getRestaurantTable();

            table.setTableStatus("AVAILABLE");

            tableRepo.save(table);

        }
        
     
        

        return savedPayment;

    }
    
 // ==========================
 // Get All Payments
 // ==========================

 public List<Payment> getAll() {

     return paymentRepo.findAll();

 }

 // ==========================
 // Get Payment By Id
 // ==========================

 public Payment getById(UUID id) {

     return paymentRepo.findById(id)
             .orElseThrow(() ->
                     new RuntimeException("Payment Not Found"));

 }

 // ==========================
 // Delete Payment
 // ==========================

 public void delete(UUID id) {

     Payment payment = getById(id);

     paymentRepo.delete(payment);

 }

} 