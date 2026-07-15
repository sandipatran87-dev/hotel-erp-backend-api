package com.cs.demo.service;

import java.math.BigDecimal;
import com.cs.demo.repo.MenuItemRepository;

import org.springframework.stereotype.Service;

import com.cs.demo.dto.DashboardResponse;
import com.cs.demo.entity.Payment;
import com.cs.demo.enums.BillStatus;
import com.cs.demo.enums.RoomStatus;
import com.cs.demo.repo.BillRepository;
import com.cs.demo.repo.PaymentRepository;
import com.cs.demo.repo.RestaurantTableRepository;
import com.cs.demo.repo.RoomRepository;

@Service
public class DashboardService {

    private final RoomRepository roomRepo;
    private final RestaurantTableRepository tableRepo;
    private final BillRepository billRepo;
    private final PaymentRepository paymentRepo;
    private final MenuItemRepository menuItemRepo;

    public DashboardService(
    		  RoomRepository roomRepo,
    	        RestaurantTableRepository tableRepo,
    	        BillRepository billRepo,
    	        PaymentRepository paymentRepo,
    	        MenuItemRepository menuItemRepo) {
    	
        this.roomRepo = roomRepo;
        this.tableRepo = tableRepo;
        this.billRepo = billRepo;
        this.paymentRepo = paymentRepo;
        this.menuItemRepo = menuItemRepo;

    }

    public DashboardResponse getDashboard() {

        DashboardResponse response = new DashboardResponse();

        response.setAvailableRooms(
                roomRepo.countByRoomStatus(RoomStatus.AVAILABLE));

        response.setBookedRooms(
                roomRepo.countByRoomStatus(RoomStatus.BOOKED));

        response.setAvailableTables(
                tableRepo.countByTableStatus("AVAILABLE"));
        
        response.setMenuItems( menuItemRepo.count());

        response.setPendingBills(
                billRepo.countByBillStatus(BillStatus.UNPAID));

        response.setPaidBills(
                billRepo.countByBillStatus(BillStatus.PAID));

        BigDecimal revenue = paymentRepo.findAll()

                .stream()

                .map(Payment::getAmount)

                .reduce(BigDecimal.ZERO, BigDecimal::add);

        response.setTotalRevenue(revenue);

        return response;

    }

}