package com.cs.demo.controller;


import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.*;

import com.cs.demo.entity.MenuItem;
import com.cs.demo.service.MenuItemService;

@RestController
@RequestMapping("/menu-items")
@CrossOrigin("*")
public class MenuItemController {

    private final MenuItemService service;

    public MenuItemController(MenuItemService service) {
        this.service = service;
    }

    // ==========================
    // Save Menu Item
    // ==========================

    @PostMapping
    public MenuItem save(
            @RequestBody MenuItem item) {

        return service.save(item);
    }

    // ==========================
    // Get All Menu Items
    // ==========================

    @GetMapping
    public List<MenuItem> getAll() {

        return service.getAll();
    }

    // ==========================
    // Get Menu Item By Id
    // ==========================

    @GetMapping("/{id}")
    public MenuItem getById(
            @PathVariable("id") UUID id) {

        return service.getById(id);
    }

    // ==========================
    // Update Menu Item
    // ==========================

    @PutMapping("/{id}")
    public MenuItem update(
            @PathVariable("id") UUID id,
            @RequestBody MenuItem item) {

        return service.update(id, item);
    }

    // ==========================
    // Delete Menu Item
    // ==========================

    @DeleteMapping("/{id}")
    public String delete(
            @PathVariable("id") UUID id) {

        service.delete(id);

        return "Menu Item Deleted Successfully";
    }

    // ==========================
    // Available Menu Items
    // ==========================

    @GetMapping("/available")
    public List<MenuItem> availableItems() {

        return service.getAvailableItems();
    }

    // ==========================
    // Search Menu Item
    // ==========================

    @GetMapping("/search")
    public List<MenuItem> search(
            @RequestParam("keyword") String keyword) {

        return service.search(keyword);
    }

    // ==========================
    // Menu By Category
    // ==========================

    @GetMapping("/category/{category}")
    public List<MenuItem> category(
            @PathVariable("category") String category) {

        return service.getByCategory(category);
    }

}