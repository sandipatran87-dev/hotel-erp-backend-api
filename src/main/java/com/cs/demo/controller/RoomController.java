package com.cs.demo.controller;


import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.*;

import com.cs.demo.entity.Room;
import com.cs.demo.service.RoomService;

@RestController
@RequestMapping("/rooms")
@CrossOrigin("*")
public class RoomController {

    private final RoomService service;

    public RoomController(RoomService service) {
        this.service = service;
    }

    // Save Room
    @PostMapping
    public Room save(@RequestBody Room room) {
        return service.save(room);
    }

    // Get All Rooms
    @GetMapping
    public List<Room> getAll() {
        return service.getAll();
    }

    // Get Room By Id
    @GetMapping("/{id}")
    public Room getById(@PathVariable("id") UUID id) {
        return service.getById(id);
    }

    // Update Room
    
    @PutMapping("/{id}")
    public Room update(@PathVariable("id") UUID id,
                       @RequestBody Room room) {

        return service.update(id, room);
    }

    // Delete Room
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") UUID id) {

        service.delete(id);

        return "Room Deleted Successfully";
    }

    // Available Rooms Count
    @GetMapping("/available-count")
    public Long availableRooms() {
        return service.availableRooms();
    }

    // Booked Rooms Count
    @GetMapping("/booked-count")
    public Long bookedRooms() {
        return service.bookedRooms();
    }
    
 // Get Rooms By Room Type
    @GetMapping("/type/{roomTypeId}")
    public List<Room> getRoomsByRoomType(
            @PathVariable("roomTypeId") UUID roomTypeId) {

        return service.getRoomsByRoomType(roomTypeId);
    }

}