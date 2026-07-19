package com.cs.demo.repo;


import java.util.UUID;


import org.springframework.data.jpa.repository.JpaRepository;

import com.cs.demo.entity.Bill;
import com.cs.demo.enums.BillStatus;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BillRepository extends JpaRepository<Bill, UUID> {
	
	  long countByBillStatus(BillStatus billStatus);

	  @Query(value = """
			  SELECT COUNT(*)
			  FROM bills b
			  LEFT JOIN bookings bk
			         ON b.room_booking_id = bk.booking_id
			  LEFT JOIN food_orders fo
			         ON b.food_order_id = fo.food_order_id
			  LEFT JOIN restaurant_bookings rb
			         ON fo.booking_id = rb.restaurant_booking_id
			  WHERE bk.customer_id = :customerId
			     OR rb.customer_id = :customerId
			  """, nativeQuery = true)
			  long countBillsByCustomer(@Param("customerId") UUID customerId);
	  
	  
}