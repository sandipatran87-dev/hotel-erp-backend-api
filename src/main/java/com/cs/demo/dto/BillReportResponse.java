package com.cs.demo.dto;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import com.cs.demo.enums.BillStatus;
import com.cs.demo.enums.BillType;

public class BillReportResponse {

    private UUID billId;
    private String customerName;
    private BillType billType;
    private BigDecimal grandTotal;
    private BillStatus billStatus;
    private LocalDateTime createdAt;

    public BillReportResponse() {
    }

    public UUID getBillId() {
        return billId;
    }

    public void setBillId(UUID billId) {
        this.billId = billId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public BillType getBillType() {
        return billType;
    }

    public void setBillType(BillType billType) {
        this.billType = billType;
    }

    public BigDecimal getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(BigDecimal grandTotal) {
        this.grandTotal = grandTotal;
    }

    public BillStatus getBillStatus() {
        return billStatus;
    }

    public void setBillStatus(BillStatus billStatus) {
        this.billStatus = billStatus;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}