package com.cs.demo.controller;




import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.*;

import com.cs.demo.entity.Bill;
import com.cs.demo.service.BillService;

@RestController
@RequestMapping("/bills")
@CrossOrigin("*")
public class BillController {

    private final BillService service;

    public BillController(BillService service) {
        this.service = service;
    }

    // ==========================
    // Generate Room Bill
    // ==========================

    @PostMapping("/room/{bookingId}")
    public Bill generateRoomBill(
            @PathVariable("bookingId") UUID bookingId) {

        return service.generateRoomBill(bookingId);
    }

    // ==========================
    // Generate Food Bill
    // ==========================

    @PostMapping("/food/{foodOrderId}")
    public Bill generateFoodBill(
            @PathVariable("foodOrderId") UUID foodOrderId) {

        return service.generateFoodBill(foodOrderId);
    }

    // ==========================
    // Get All Bills
    // ==========================

    @GetMapping
    public List<Bill> getAll() {

        return service.getAll();
    }

    // ==========================
    // Get Bill By Id
    // ==========================

    @GetMapping("/{id}")
    public Bill getById(
            @PathVariable("id") UUID id) {

        return service.getById(id);
    }

    // ==========================
    // Delete Bill
    // ==========================

    @DeleteMapping("/{id}")
    public String delete(
            @PathVariable("id") UUID id) {

        service.delete(id);

        return "Bill Deleted Successfully";
    }

}