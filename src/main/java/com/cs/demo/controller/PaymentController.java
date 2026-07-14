package com.cs.demo.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.*;

import com.cs.demo.entity.Payment;
import com.cs.demo.service.PaymentService;

@RestController
@RequestMapping("/payments")
@CrossOrigin("*")
public class PaymentController {

    private final PaymentService service;

    public PaymentController(PaymentService service) {
        this.service = service;
    }

    // ==========================
    // Make Payment
    // ==========================

    @PostMapping
    public Payment makePayment(
            @RequestBody Payment payment) {

        return service.makePayment(payment);

    }

    // ==========================
    // Get All Payments
    // ==========================

    @GetMapping
    public List<Payment> getAll() {

        return service.getAll();

    }

    // ==========================
    // Get Payment By Id
    // ==========================

    @GetMapping("/{id}")
    public Payment getById(
            @PathVariable("id") UUID id) {

        return service.getById(id);

    }

    // ==========================
    // Delete Payment
    // ==========================

    @DeleteMapping("/{id}")
    public String delete(
            @PathVariable("id") UUID id) {

        service.delete(id);

        return "Payment Deleted Successfully";

    }

}