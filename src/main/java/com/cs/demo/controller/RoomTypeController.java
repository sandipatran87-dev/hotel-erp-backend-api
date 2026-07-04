package com.cs.demo.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.*;

import com.cs.demo.entity.RoomType;
import com.cs.demo.service.RoomTypeService;

@RestController
@RequestMapping("/room-types")
public class RoomTypeController {

    private final RoomTypeService service;

    public RoomTypeController(RoomTypeService service) {
        this.service = service;
    }

    @PostMapping
    public RoomType save(@RequestBody RoomType roomType) {
        return service.save(roomType);
    }

    @GetMapping
    public List<RoomType> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public RoomType getById(@PathVariable("id") UUID id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public RoomType update(
            @PathVariable("id") UUID id,
            @RequestBody RoomType roomType) {

        return service.update(id, roomType);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") UUID id) {

        service.delete(id);

        return "Room Type Deleted Successfully";
    }
}