package com.cs.demo.repo;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cs.demo.entity.Bill;
import com.cs.demo.entity.Payment;
import java.time.LocalDateTime;

import org.springframework.data.repository.query.Param;


public interface PaymentRepository extends JpaRepository<Payment, UUID> {

	  Optional<Payment> findByBill(Bill bill);
	  
	  @Query("SELECT COALESCE(SUM(p.amount),0) FROM Payment p")
	    BigDecimal getTotalRevenue();

	  
	  @Query("""
		       SELECT COALESCE(SUM(p.amount), 0)
		       FROM Payment p
		       WHERE p.paymentDate BETWEEN :startDate AND :endDate
		       """)
		BigDecimal getRevenueBetween(
		        @Param("startDate") LocalDateTime startDate,
		        @Param("endDate") LocalDateTime endDate
		);

	  
	  @Query("""
		       SELECT COALESCE(SUM(p.amount), 0)
		       FROM Payment p
		       WHERE p.paymentDate BETWEEN :startDate AND :endDate
		       """)
		BigDecimal getMonthlyRevenue(
		        @Param("startDate") LocalDateTime startDate,
		        @Param("endDate") LocalDateTime endDate
		);
}