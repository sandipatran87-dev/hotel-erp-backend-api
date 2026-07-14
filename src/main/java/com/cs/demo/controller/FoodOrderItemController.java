package com.cs.demo.controller;


import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.*;

import com.cs.demo.entity.FoodOrderItem;
import com.cs.demo.service.FoodOrderItemService;

@RestController
@RequestMapping("/food-order-items")
@CrossOrigin("*")
public class FoodOrderItemController {

    private final FoodOrderItemService service;

    public FoodOrderItemController(FoodOrderItemService service) {

        this.service = service;

    }

    // ==========================
    // Save Food Order Item
    // ==========================

    @PostMapping
    public FoodOrderItem save(
            @RequestBody FoodOrderItem item) {

        return service.save(item);

    }

    // ==========================
    // Get All Food Order Items
    // ==========================

    @GetMapping
    public List<FoodOrderItem> getAll() {

        return service.getAll();

    }

    // ==========================
    // Get Food Order Item By Id
    // ==========================

    @GetMapping("/{id}")
    public FoodOrderItem getById(
            @PathVariable("id") UUID id) {

        return service.getById(id);

    }
    
 // ==========================
 // Get Items By Food Order
 // ==========================

 @GetMapping("/food-order/{foodOrderId}")
 public List<FoodOrderItem> getByFoodOrder(

         @PathVariable("foodOrderId") UUID foodOrderId) {

     return service.getByFoodOrder(foodOrderId);

 }
 
//==========================
//Update Quantity
//==========================

@PutMapping("/{id}")

public FoodOrderItem updateQuantity(

      @PathVariable("id") UUID id,

      @RequestParam("quantity") Integer quantity) {

  return service.updateQuantity(id, quantity);

}
    

    // ==========================
    // Delete Food Order Item
    // ==========================

    @DeleteMapping("/{id}")
    public String delete(
            @PathVariable("id") UUID id) {

        service.delete(id);

        return "Food Order Item Deleted Successfully";

    }

}