package com.cs.demo.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.cs.demo.entity.FoodOrder;
import com.cs.demo.repo.FoodOrderRepository;

@Service
public class FoodOrderService {

    private final FoodOrderRepository repo;

    public FoodOrderService(FoodOrderRepository repo) {

        this.repo = repo;

    }

    // ==========================
    // Save Food Order
    // ==========================

    public FoodOrder save(FoodOrder order) {

        if (order.getOrderStatus() == null ||
                order.getOrderStatus().isBlank()) {

            order.setOrderStatus("PLACED");

        }

        return repo.save(order);

    }

    // ==========================
    // Get All Orders
    // ==========================

    public List<FoodOrder> getAll() {

        return repo.findAll();

    }

    // ==========================
    // Get Order By Id
    // ==========================

    public FoodOrder getById(UUID id) {

        return repo.findById(id)

                .orElseThrow(() ->

                        new RuntimeException("Food Order Not Found"));

    }
    
 // ==========================
 // Get Food Order By Table Id
 // ==========================

 public FoodOrder getByTableId(UUID tableId) {

     return repo.findByRestaurantBooking_RestaurantTable_TableId(tableId)

             .orElseThrow(() ->

                     new RuntimeException("Food Order Not Found"));

 }

}