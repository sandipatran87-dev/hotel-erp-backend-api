package com.cs.demo.dto;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public class RestaurantBookingReportResponse {

    private UUID restaurantBookingId;
    private String customerName;
    private String tableNumber;
    private LocalDate bookingDate;
    private LocalTime bookingTime;
    private Integer guests;
    private String bookingStatus;

    public RestaurantBookingReportResponse() {
    }

    public UUID getRestaurantBookingId() {
        return restaurantBookingId;
    }

    public void setRestaurantBookingId(UUID restaurantBookingId) {
        this.restaurantBookingId = restaurantBookingId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(String tableNumber) {
        this.tableNumber = tableNumber;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    public LocalTime getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(LocalTime bookingTime) {
        this.bookingTime = bookingTime;
    }

    public Integer getGuests() {
        return guests;
    }

    public void setGuests(Integer guests) {
        this.guests = guests;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }
}