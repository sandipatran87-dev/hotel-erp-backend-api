package com.cs.demo.dto;

import java.math.BigDecimal;

public class DashboardResponse {

    private long availableRooms;
    private long bookedRooms;
    private long availableTables;
    private long pendingBills;
    private long paidBills;
    private BigDecimal totalRevenue;
    private long menuItems;

    public long getMenuItems() {
		return menuItems;
	}

	public void setMenuItems(long menuItems) {
		this.menuItems = menuItems;
	}

	public DashboardResponse() {
    }

    public long getAvailableRooms() {
        return availableRooms;
    }

    public void setAvailableRooms(long availableRooms) {
        this.availableRooms = availableRooms;
    }

    public long getBookedRooms() {
        return bookedRooms;
    }

    public void setBookedRooms(long bookedRooms) {
        this.bookedRooms = bookedRooms;
    }

    public long getAvailableTables() {
        return availableTables;
    }

    public void setAvailableTables(long availableTables) {
        this.availableTables = availableTables;
    }

    public long getPendingBills() {
        return pendingBills;
    }

    public void setPendingBills(long pendingBills) {
        this.pendingBills = pendingBills;
    }

    public long getPaidBills() {
        return paidBills;
    }

    public void setPaidBills(long paidBills) {
        this.paidBills = paidBills;
    }

    public BigDecimal getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(BigDecimal totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

}