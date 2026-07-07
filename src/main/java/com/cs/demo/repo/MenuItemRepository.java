package com.cs.demo.repo;



import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cs.demo.entity.MenuItem;

public interface MenuItemRepository extends JpaRepository<MenuItem, UUID> {

    // Find Available Menu Items
    List<MenuItem> findByAvailableTrue();

    // Find By Category
    List<MenuItem> findByCategory(String category);

    // Search By Item Name
    List<MenuItem> findByItemNameContainingIgnoreCase(String keyword);
}