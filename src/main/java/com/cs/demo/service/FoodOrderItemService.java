package com.cs.demo.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.cs.demo.entity.FoodOrder;
import com.cs.demo.entity.FoodOrderItem;
import com.cs.demo.entity.MenuItem;
import com.cs.demo.repo.FoodOrderItemRepository;
import com.cs.demo.repo.FoodOrderRepository;
import com.cs.demo.repo.MenuItemRepository;

@Service
public class FoodOrderItemService {

    private final FoodOrderItemRepository itemRepo;
    private final FoodOrderRepository orderRepo;
    private final MenuItemRepository menuRepo;

    public FoodOrderItemService(
            FoodOrderItemRepository itemRepo,
            FoodOrderRepository orderRepo,
            MenuItemRepository menuRepo) {

        this.itemRepo = itemRepo;
        this.orderRepo = orderRepo;
        this.menuRepo = menuRepo;
    }

    // ==========================
    // Save Food Order Item
    // ==========================
    public FoodOrderItem save(FoodOrderItem item) {

        FoodOrder order = orderRepo.findById(
                item.getFoodOrder().getFoodOrderId()
        ).orElseThrow(() ->
                new RuntimeException("Food Order Not Found"));

        MenuItem menu = menuRepo.findById(
                item.getMenuItem().getMenuItemId()
        ).orElseThrow(() ->
                new RuntimeException("Menu Item Not Found"));
        
     // ==========================
     // Check Existing Item
     // ==========================

     Optional<FoodOrderItem> existingItem =
             itemRepo.findByFoodOrderAndMenuItem(order, menu);

     if (existingItem.isPresent()) {

         FoodOrderItem oldItem = existingItem.get();

         // Update Quantity
         oldItem.setQuantity(
                 oldItem.getQuantity() + item.getQuantity());
         
         oldItem.setPrice(menu.getPrice());


         // Update Subtotal
         BigDecimal subtotal = menu.getPrice().multiply(
                 BigDecimal.valueOf(oldItem.getQuantity()));

         oldItem.setSubtotal(subtotal);

         // Save Updated Item
         itemRepo.save(oldItem);

         // Recalculate Total
         List<FoodOrderItem> items =
                 itemRepo.findByFoodOrder(order);

         BigDecimal total = BigDecimal.ZERO;

         for (FoodOrderItem orderItem : items) {

             total = total.add(orderItem.getSubtotal());

         }

         order.setTotalAmount(total);

         orderRepo.save(order);

         return oldItem;
     }
        
        

        // Set References
        item.setFoodOrder(order);
        item.setMenuItem(menu);

        // Price
        item.setPrice(menu.getPrice());

        // Subtotal = Price × Quantity
        BigDecimal subtotal = menu.getPrice().multiply(
                BigDecimal.valueOf(item.getQuantity()));

        item.setSubtotal(subtotal);

        // Save Item
        FoodOrderItem savedItem = itemRepo.save(item);

        // ==========================
        // Calculate Grand Total
        // ==========================

        List<FoodOrderItem> orderItems =
                itemRepo.findByFoodOrder(order);

        BigDecimal grandTotal = BigDecimal.ZERO;

        for (FoodOrderItem orderItem : orderItems) {

            grandTotal = grandTotal.add(
                    orderItem.getSubtotal());

        }

        // Update Food Order Total
        order.setTotalAmount(grandTotal);

        orderRepo.save(order);

        return savedItem;
    }

    // ==========================
    // Get All
    // ==========================

    public List<FoodOrderItem> getAll() {

        return itemRepo.findAll();

    }

    // ==========================
    // Get By Id
    // ==========================

    public FoodOrderItem getById(UUID id) {

        return itemRepo.findById(id)

                .orElseThrow(() ->

                        new RuntimeException("Food Order Item Not Found"));

    }
    
    
 // ==========================
 // Get Items By Food Order
 // ==========================

 public List<FoodOrderItem> getByFoodOrder(UUID foodOrderId) {

     return itemRepo.findByFoodOrder_FoodOrderId(foodOrderId);

 }
 
 
//==========================
//Update Quantity
//==========================

public FoodOrderItem updateQuantity(UUID id, Integer quantity) {

  FoodOrderItem item = getById(id);

  item.setQuantity(quantity);

  BigDecimal subtotal = item.getPrice().multiply(
          BigDecimal.valueOf(quantity));

  item.setSubtotal(subtotal);

  itemRepo.save(item);

  // Recalculate Grand Total
  FoodOrder order = item.getFoodOrder();

  List<FoodOrderItem> items =
          itemRepo.findByFoodOrder(order);

  BigDecimal total = BigDecimal.ZERO;

  for (FoodOrderItem orderItem : items) {

      total = total.add(orderItem.getSubtotal());

  }

  order.setTotalAmount(total);

  orderRepo.save(order);

  return item;
}
    

    // ==========================
    // Delete
    // ==========================

    public void delete(UUID id) {

        FoodOrderItem item = getById(id);

        itemRepo.delete(item);

    }

}