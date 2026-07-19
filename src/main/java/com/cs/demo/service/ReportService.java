package com.cs.demo.service;

import org.springframework.stereotype.Service;

import com.cs.demo.dto.ReportResponse;
import com.cs.demo.repo.BookingRepository;
import com.cs.demo.repo.PaymentRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;

import java.util.List;
import java.util.stream.Collectors;

import com.cs.demo.dto.BookingReportResponse;
import com.cs.demo.dto.PaymentReportResponse;
import com.cs.demo.entity.Booking;
import com.cs.demo.entity.Payment;

import com.cs.demo.dto.RestaurantBookingReportResponse;
import com.cs.demo.entity.RestaurantBooking;
import com.cs.demo.repo.RestaurantBookingRepository;

import com.cs.demo.dto.BillReportResponse;
import com.cs.demo.entity.Bill;
import com.cs.demo.repo.BillRepository;

import com.cs.demo.dto.CustomerReportResponse;
import com.cs.demo.entity.Customer;
import com.cs.demo.repo.CustomerRepository;

@Service
public class ReportService {

    private final PaymentRepository paymentRepo;
    private final BookingRepository bookingRepo;
    private final RestaurantBookingRepository restaurantBookingRepo;
    private final BillRepository billRepo;
    private final CustomerRepository customerRepo;

    public ReportService(
            PaymentRepository paymentRepo,
            BookingRepository bookingRepo,
            RestaurantBookingRepository restaurantBookingRepo,
            BillRepository billRepo,
            CustomerRepository customerRepo) {

        this.paymentRepo = paymentRepo;
        this.bookingRepo = bookingRepo;
        this.restaurantBookingRepo = restaurantBookingRepo;
        this.billRepo = billRepo;
        this.customerRepo = customerRepo;
    }

    public ReportResponse getRevenueReport() {

        ReportResponse response = new ReportResponse();

        response.setTotalRevenue(
                paymentRepo.getTotalRevenue());

        response.setTotalPayments(
                paymentRepo.count());
       

        return response;

    }
    
    public BigDecimal getTodayRevenue() {

        LocalDate today = LocalDate.now();

        LocalDateTime start = today.atStartOfDay();

        LocalDateTime end = today.atTime(23, 59, 59);

        return paymentRepo.getRevenueBetween(start, end);

    }
    
    public BigDecimal getMonthlyRevenue() {

        YearMonth currentMonth = YearMonth.now();

        LocalDateTime start = currentMonth
                .atDay(1)
                .atStartOfDay();

        LocalDateTime end = currentMonth
                .atEndOfMonth()
                .atTime(23, 59, 59);

        return paymentRepo.getMonthlyRevenue(start, end);

    }
    
    public BigDecimal getRevenueBetween(LocalDate fromDate,
            LocalDate toDate) {

LocalDateTime start = fromDate.atStartOfDay();

LocalDateTime end = toDate.atTime(23, 59, 59);

return paymentRepo.getRevenueBetween(start, end);

}
    
    public List<PaymentReportResponse> getAllPaymentsReport() {

        return paymentRepo.findAll()
                .stream()
                .map(this::convertToPaymentReport)
                .collect(Collectors.toList());

    }
    
    public List<BookingReportResponse> getRoomBookingReport() {

        return bookingRepo.findAll()
                .stream()
                .map(this::convertToBookingReport)
                .collect(Collectors.toList());

    }
    
    
    public List<RestaurantBookingReportResponse> getRestaurantBookingReport() {

        return restaurantBookingRepo.findAll()
                .stream()
                .map(this::convertToRestaurantBookingReport)
                .collect(Collectors.toList());
    }
    
    
    private PaymentReportResponse convertToPaymentReport(Payment payment) {

        PaymentReportResponse response = new PaymentReportResponse();

        response.setPaymentId(payment.getPaymentId());

        response.setBillId(payment.getBill().getBillId());

        response.setAmount(payment.getAmount());

        response.setPaymentMethod(payment.getPaymentMethod());

        response.setPaymentStatus(payment.getPaymentStatus());

        response.setPaymentDate(payment.getPaymentDate());

        return response;

    }
    
   
    private BookingReportResponse convertToBookingReport(Booking booking) {

        BookingReportResponse response = new BookingReportResponse();

        response.setBookingId(booking.getBookingId());

        response.setCustomerName(
                booking.getCustomer().getFirstName() + " "
                + booking.getCustomer().getLastName());

        response.setRoomNumber(
                booking.getRoom().getRoomNumber());

        response.setRoomType(
                booking.getRoom().getRoomType().getTypeName());

        response.setCheckInDate(
                booking.getCheckInDate());

        response.setCheckOutDate(
                booking.getCheckOutDate());

        response.setTotalDays(
                booking.getTotalDays());

        response.setTotalAmount(
                booking.getTotalAmount());

        response.setBookingStatus(
                booking.getBookingStatus());

        return response;
    }

    
    private RestaurantBookingReportResponse convertToRestaurantBookingReport(
            RestaurantBooking booking) {

        RestaurantBookingReportResponse response =
                new RestaurantBookingReportResponse();

        response.setRestaurantBookingId(
                booking.getRestaurantBookingId());

        response.setCustomerName(
                booking.getCustomer().getFirstName() + " "
                        + booking.getCustomer().getLastName());

        response.setTableNumber(
                booking.getRestaurantTable().getTableNumber());

        response.setBookingDate(
                booking.getBookingDate());

        response.setBookingTime(
                booking.getBookingTime());

        response.setGuests(
                booking.getGuests());

        response.setBookingStatus(
                booking.getBookingStatus());

        return response;
    }
    
    public List<BillReportResponse> getBillsReport() {

        return billRepo.findAll()
                .stream()
                .map(this::convertToBillReport)
                .collect(Collectors.toList());

    }
    
    private BillReportResponse convertToBillReport(Bill bill) {

        BillReportResponse response = new BillReportResponse();

        response.setBillId(bill.getBillId());

        // Customer Name
        if (bill.getBillType().name().equals("ROOM")) {

            response.setCustomerName(
                    bill.getRoomBooking()
                            .getCustomer()
                            .getFirstName() + " "
                            + bill.getRoomBooking()
                            .getCustomer()
                            .getLastName());

        } else {

            response.setCustomerName(
                    bill.getFoodOrder()
                            .getRestaurantBooking()
                            .getCustomer()
                            .getFirstName() + " "
                            + bill.getFoodOrder()
                            .getRestaurantBooking()
                            .getCustomer()
                            .getLastName());
        }

        response.setBillType(bill.getBillType());
        response.setGrandTotal(bill.getGrandTotal());
        response.setBillStatus(bill.getBillStatus());
        response.setCreatedAt(bill.getCreatedAt());

        return response;
    }
    
    public List<CustomerReportResponse> getCustomerReport() {

        return customerRepo.findAll()
                .stream()
                .map(this::convertToCustomerReport)
                .collect(Collectors.toList());

    }
    
    private CustomerReportResponse convertToCustomerReport(Customer customer) {

        CustomerReportResponse response = new CustomerReportResponse();

        response.setCustomerId(customer.getCustomerId());

        response.setCustomerName(
                customer.getFirstName() + " " + customer.getLastName());

        response.setMobile(customer.getMobile());

        response.setEmail(customer.getEmail());

        response.setTotalRoomBookings(
                bookingRepo.countByCustomer(customer));

        response.setTotalRestaurantBookings(
                restaurantBookingRepo.countByCustomer(customer));

        response.setTotalBills(
                billRepo.countBillsByCustomer(customer.getCustomerId()));

        return response;
    }
}