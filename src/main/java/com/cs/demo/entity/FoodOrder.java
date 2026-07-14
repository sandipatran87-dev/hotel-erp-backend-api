package com.cs.demo.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;

@Entity
@Table(name = "food_orders")
public class FoodOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "food_order_id")
    private UUID foodOrderId;

    @ManyToOne
    @JoinColumn(name = "booking_id", nullable = false)
    private RestaurantBooking restaurantBooking;

    @Column(name = "order_status", nullable = false)
    private String orderStatus;

    @Column(name = "total_amount", nullable = false)
    private BigDecimal totalAmount = BigDecimal.ZERO;

    @CreationTimestamp
    @Column(name = "order_time", updatable = false)
    private LocalDateTime orderTime;

    public FoodOrder() {
    }

    public UUID getFoodOrderId() {
        return foodOrderId;
    }

    public void setFoodOrderId(UUID foodOrderId) {
        this.foodOrderId = foodOrderId;
    }

    public RestaurantBooking getRestaurantBooking() {
        return restaurantBooking;
    }

    public void setRestaurantBooking(RestaurantBooking restaurantBooking) {
        this.restaurantBooking = restaurantBooking;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }
}