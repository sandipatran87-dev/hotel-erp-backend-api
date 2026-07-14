package com.cs.demo.repo;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cs.demo.entity.Bill;
import com.cs.demo.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, UUID> {

	  Optional<Payment> findByBill(Bill bill);

}