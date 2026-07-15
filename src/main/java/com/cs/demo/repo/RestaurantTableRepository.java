package com.cs.demo.repo;



import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cs.demo.entity.RestaurantTable;

public interface RestaurantTableRepository extends JpaRepository<RestaurantTable, UUID> {
	
	long countByTableStatus(String tableStatus);

}