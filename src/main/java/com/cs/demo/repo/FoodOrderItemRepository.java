package com.cs.demo.repo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cs.demo.entity.FoodOrder;
import com.cs.demo.entity.FoodOrderItem;
import com.cs.demo.entity.MenuItem;

public interface FoodOrderItemRepository extends JpaRepository<FoodOrderItem, UUID> {
	List<FoodOrderItem> findByFoodOrder(FoodOrder foodOrder);
	
	 Optional<FoodOrderItem> findByFoodOrderAndMenuItem( FoodOrder foodOrder,MenuItem menuItem);
	 
	 List<FoodOrderItem> findByFoodOrder_FoodOrderId(UUID foodOrderId);

}