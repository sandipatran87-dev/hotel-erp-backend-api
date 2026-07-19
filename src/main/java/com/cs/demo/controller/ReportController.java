package com.cs.demo.controller;

import java.math.BigDecimal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cs.demo.dto.BillReportResponse;
import com.cs.demo.dto.BookingReportResponse;
import com.cs.demo.dto.CustomerReportResponse;
import com.cs.demo.dto.PaymentReportResponse;
import com.cs.demo.dto.ReportResponse;
import com.cs.demo.dto.RestaurantBookingReportResponse;
import com.cs.demo.service.ReportService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/reports")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/revenue")
    public ReportResponse getRevenueReport() {
        return reportService.getRevenueReport();
    }

    @GetMapping("/revenue/today")
    public BigDecimal getTodayRevenue() {

        return reportService.getTodayRevenue();

    }
    
    @GetMapping("/revenue/monthly")
    public BigDecimal getMonthlyRevenue() {

        return reportService.getMonthlyRevenue();

    }
    
    @GetMapping("/revenue/range")
    public BigDecimal getRevenueBetween(

            @RequestParam("from")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate from,

            @RequestParam("to")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate to) {

        return reportService.getRevenueBetween(from, to);

    }
    
    @GetMapping("/payments")
    public List<PaymentReportResponse> getAllPaymentsReport() {

        return reportService.getAllPaymentsReport();

    }
    
    @GetMapping("/room-bookings")
    public List<BookingReportResponse> getRoomBookingReport() {

        return reportService.getRoomBookingReport();

    }
    
    @GetMapping("/restaurant-bookings")
    public List<RestaurantBookingReportResponse> getRestaurantBookingReport() {

        return reportService.getRestaurantBookingReport();
    }
    
    @GetMapping("/bills")
    public List<BillReportResponse> getBillsReport() {

        return reportService.getBillsReport();

    }
    
    @GetMapping("/customers")
    public List<CustomerReportResponse> getCustomerReport() {

        return reportService.getCustomerReport();

    }
    
}