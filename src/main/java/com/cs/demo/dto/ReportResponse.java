package com.cs.demo.dto;


import java.math.BigDecimal;

public class ReportResponse {

    private BigDecimal totalRevenue;

    private long totalPayments;

    public ReportResponse() {
    }

    public BigDecimal getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(BigDecimal totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public long getTotalPayments() {
        return totalPayments;
    }

    public void setTotalPayments(long totalPayments) {
        this.totalPayments = totalPayments;
    }

}