package com.cs.demo.repo;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cs.demo.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {

	
	@Query("SELECT COUNT(c) FROM Customer c")Long totalCustomers();
	
}