package com.cs.demo.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.cs.demo.entity.MenuItem;
import com.cs.demo.repo.MenuItemRepository;

@Service
public class MenuItemService {

    private final MenuItemRepository repo;

    public MenuItemService(MenuItemRepository repo) {
        this.repo = repo;
    }

    // ==========================
    // Save Menu Item
    // ==========================

    public MenuItem save(MenuItem item) {

        if (item.getAvailable() == null) {
            item.setAvailable(true);
        }

        return repo.save(item);
    }

    // ==========================
    // Get All Menu Items
    // ==========================

    public List<MenuItem> getAll() {
        return repo.findAll();
    }

    // ==========================
    // Get Menu Item By Id
    // ==========================

    public MenuItem getById(UUID id) {

        return repo.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Menu Item Not Found"));
    }

    // ==========================
    // Update Menu Item
    // ==========================

    public MenuItem update(UUID id, MenuItem item) {

        MenuItem existing = getById(id);

        existing.setItemName(item.getItemName());
        existing.setCategory(item.getCategory());
        existing.setPrice(item.getPrice());
        existing.setDescription(item.getDescription());
        existing.setAvailable(item.getAvailable());
        existing.setImageUrl(item.getImageUrl());
        return repo.save(existing);
    }

    // ==========================
    // Delete Menu Item
    // ==========================

    public void delete(UUID id) {

        MenuItem item = getById(id);

        repo.delete(item);
    }

    // ==========================
    // Get Available Menu Items
    // ==========================

    public List<MenuItem> getAvailableItems() {

        return repo.findByAvailableTrue();
    }

    // ==========================
    // Search Menu Item
    // ==========================

    public List<MenuItem> search(String keyword) {

        return repo.findByItemNameContainingIgnoreCase(keyword);
    }

    // ==========================
    // Get Menu By Category
    // ==========================

    public List<MenuItem> getByCategory(String category) {

        return repo.findByCategory(category);
    }

}