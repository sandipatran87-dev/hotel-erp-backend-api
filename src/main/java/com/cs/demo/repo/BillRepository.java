package com.cs.demo.repo;


import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cs.demo.entity.Bill;

public interface BillRepository extends JpaRepository<Bill, UUID> {

}