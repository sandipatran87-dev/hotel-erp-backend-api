package com.cs.demo.repo;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cs.demo.entity.FoodOrder;

public interface FoodOrderRepository extends JpaRepository<FoodOrder, UUID> {
	  
	  Optional<FoodOrder> findByRestaurantBooking_RestaurantTable_TableId(UUID tableId);

	
}