package com.cs.demo.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.*;

import com.cs.demo.entity.FoodOrder;
import com.cs.demo.service.FoodOrderService;

@RestController
@RequestMapping("/food-orders")
@CrossOrigin("*")
public class FoodOrderController {

    private final FoodOrderService service;

    public FoodOrderController(FoodOrderService service) {
        this.service = service;
    }

    // ==========================
    // Save Food Order
    // ==========================

    @PostMapping
    public FoodOrder save(
            @RequestBody FoodOrder order) {

        return service.save(order);
    }

    // ==========================
    // Get All Food Orders
    // ==========================

    @GetMapping
    public List<FoodOrder> getAll() {

        return service.getAll();
    }

    // ==========================
    // Get Food Order By Id
    // ==========================

    @GetMapping("/{id}")
    public FoodOrder getById(
            @PathVariable("id") UUID id) {

        return service.getById(id);
    }
    
 // ==========================
 // Get Food Order By Table Id
 // ==========================
    @GetMapping("/table/{tableId}")
    public FoodOrder getByTableId(
            @PathVariable("tableId") UUID tableId) {

        return service.getByTableId(tableId);

    }
 
}