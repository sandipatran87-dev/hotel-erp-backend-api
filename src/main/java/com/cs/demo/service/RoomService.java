package com.cs.demo.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.cs.demo.entity.Room;
import com.cs.demo.repo.RoomRepository;
import com.cs.demo.enums.RoomStatus;
@Service
public class RoomService {

    private final RoomRepository roomRepo;

    public RoomService(RoomRepository roomRepo) {
        this.roomRepo = roomRepo;
    }
    
 // Get Rooms By Room Type
    public List<Room> getRoomsByRoomType(UUID roomTypeId) {

        return roomRepo.findByRoomTypeRoomTypeId(roomTypeId);

    }
    
    

    // Save Room
    public Room save(Room room) {

        if (roomRepo.existsByRoomNumber(room.getRoomNumber())) {
            throw new RuntimeException("Room Number Already Exists");
        }

        return roomRepo.save(room);
    }

    // Get All Rooms
    public List<Room> getAll() {
        return roomRepo.findAll();
    }

    // Get Room By Id
    public Room getById(UUID id) {
        return roomRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Room Not Found"));
    }

    // Update Room
    public Room update(UUID id, Room room) {

        Room existing = getById(id);

        if (!existing.getRoomNumber().equals(room.getRoomNumber())
                && roomRepo.existsByRoomNumber(room.getRoomNumber())) {
            throw new RuntimeException("Room Number Already Exists");
        }

        existing.setRoomType(room.getRoomType());
        existing.setFloorNo(room.getFloorNo());
        existing.setRoomNumber(room.getRoomNumber());
        existing.setRoomStatus(room.getRoomStatus());
        existing.setImageUrl(room.getImageUrl());

        return roomRepo.save(existing);
    }

    // Delete Room
    public void delete(UUID id) {

        Room room = getById(id);

        roomRepo.delete(room);
    }

    

    public Long availableRooms() {
        return roomRepo.countByRoomStatus(RoomStatus.AVAILABLE);
    }

    public Long bookedRooms() {
        return roomRepo.countByRoomStatus(RoomStatus.BOOKED);
    }
    
 // Maintenance Rooms Count
    public Long maintenanceRooms() {
        return roomRepo.countByRoomStatus(RoomStatus.MAINTENANCE);
    }

}