package com.cs.demo.dto;


import java.util.UUID;

public class CustomerReportResponse {

    private UUID customerId;
    private String customerName;
    private String mobile;
    private String email;
    private Long totalRoomBookings;
    private Long totalRestaurantBookings;
    private Long totalBills;

    public CustomerReportResponse() {
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getTotalRoomBookings() {
        return totalRoomBookings;
    }

    public void setTotalRoomBookings(Long totalRoomBookings) {
        this.totalRoomBookings = totalRoomBookings;
    }

    public Long getTotalRestaurantBookings() {
        return totalRestaurantBookings;
    }

    public void setTotalRestaurantBookings(Long totalRestaurantBookings) {
        this.totalRestaurantBookings = totalRestaurantBookings;
    }

    public Long getTotalBills() {
        return totalBills;
    }

    public void setTotalBills(Long totalBills) {
        this.totalBills = totalBills;
    }
}