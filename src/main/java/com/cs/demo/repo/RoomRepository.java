package com.cs.demo.repo;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cs.demo.entity.Room;
import com.cs.demo.enums.RoomStatus;

public interface RoomRepository extends JpaRepository<Room, UUID> {

    boolean existsByRoomNumber(String roomNumber);

    Long countByRoomStatus(RoomStatus roomStatus);

    List<Room> findByRoomStatus(RoomStatus roomStatus);
    
    List<Room> findByRoomTypeRoomTypeId(UUID roomTypeId);

}