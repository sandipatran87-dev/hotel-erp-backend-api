package com.cs.demo.dto;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import com.cs.demo.enums.PaymentMethod;
import com.cs.demo.enums.PaymentStatus;

public class PaymentReportResponse {

    private UUID paymentId;

    private UUID billId;

    private BigDecimal amount;

    private PaymentMethod paymentMethod;

    private PaymentStatus paymentStatus;

    private LocalDateTime paymentDate;
    
    // Generate Getters and Setters

    public PaymentReportResponse() {
    }

	public UUID getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(UUID paymentId) {
		this.paymentId = paymentId;
	}

	public UUID getBillId() {
		return billId;
	}

	public void setBillId(UUID billId) {
		this.billId = billId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public LocalDateTime getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDateTime paymentDate) {
		this.paymentDate = paymentDate;
	}

   
    
}