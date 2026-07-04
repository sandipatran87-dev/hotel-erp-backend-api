package com.cs.demo.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.cs.demo.entity.RoomType;
import com.cs.demo.repo.RoomTypeRepository;

@Service
public class RoomTypeService {

    private final RoomTypeRepository roomTypeRepository;

    public RoomTypeService(RoomTypeRepository roomTypeRepository) {
        this.roomTypeRepository = roomTypeRepository;
    }

    // Save Room Type
    public RoomType save(RoomType roomType) {
        return roomTypeRepository.save(roomType);
    }

    // Get All Room Types
    public List<RoomType> getAll() {
        return roomTypeRepository.findAll();
    }

    // Get Room Type By Id
    public RoomType getById(UUID id) {
        return roomTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room Type Not Found with ID : " + id));
    }

    // Update Room Type
    public RoomType update(UUID id, RoomType roomType) {

        RoomType existingRoomType = getById(id);

        existingRoomType.setTypeName(roomType.getTypeName());
        existingRoomType.setDescription(roomType.getDescription());
        existingRoomType.setBasePrice(roomType.getBasePrice());
        existingRoomType.setMaxCapacity(roomType.getMaxCapacity());

        return roomTypeRepository.save(existingRoomType);
    }

    // Delete Room Type
    public void delete(UUID id) {

        RoomType roomType = getById(id);

        roomTypeRepository.delete(roomType);
    }
}