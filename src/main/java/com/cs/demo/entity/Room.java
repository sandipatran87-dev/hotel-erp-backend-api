package com.cs.demo.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import com.cs.demo.enums.RoomStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "room_id")
    private UUID roomId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_type_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private RoomType roomType;
    @Column(name = "room_number", nullable = false, unique = true, length = 10)
    private String roomNumber;

    @Column(name = "floor_no", nullable = false)
    private Integer floorNo;

    @Enumerated(EnumType.STRING)
    @Column(name = "room_status", nullable = false)
    private RoomStatus roomStatus = RoomStatus.AVAILABLE;

    @Column(name = "image_url")
    private String imageUrl;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    
    
 // Getters & Setters

	public UUID getRoomId() {
		return roomId;
	}

	public void setRoomId(UUID roomId) {
		this.roomId = roomId;
	}

	public RoomType getRoomType() {
		return roomType;
	}

	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	public Integer getFloorNo() {
		return floorNo;
	}

	public void setFloorNo(Integer floorNo) {
		this.floorNo = floorNo;
	}

	public RoomStatus getRoomStatus() {
		return roomStatus;
	}

	public void setRoomStatus(RoomStatus roomStatus) {
		this.roomStatus = roomStatus;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
   
 
   
    
    
    
}