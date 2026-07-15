package com.cs.demo.repo;


import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cs.demo.entity.Bill;
import com.cs.demo.enums.BillStatus;

public interface BillRepository extends JpaRepository<Bill, UUID> {
	
	  long countByBillStatus(BillStatus billStatus);


}