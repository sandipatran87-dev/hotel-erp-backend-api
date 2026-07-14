package com.cs.demo.service;




import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.cs.demo.entity.Bill;
import com.cs.demo.entity.Booking;
import com.cs.demo.entity.FoodOrder;
import com.cs.demo.entity.FoodOrderItem;
import com.cs.demo.enums.BillStatus;
import com.cs.demo.enums.BillType;
import com.cs.demo.repo.BillRepository;
import com.cs.demo.repo.BookingRepository;
import com.cs.demo.repo.FoodOrderItemRepository;
import com.cs.demo.repo.FoodOrderRepository;

@Service
public class BillService {

    private final BillRepository billRepo;
    private final BookingRepository bookingRepo;
    private final FoodOrderRepository foodOrderRepo;
    private final FoodOrderItemRepository foodOrderItemRepo;

    public BillService(
            BillRepository billRepo,
            BookingRepository bookingRepo,
            FoodOrderRepository foodOrderRepo,
            FoodOrderItemRepository foodOrderItemRepo) {

        this.billRepo = billRepo;
        this.bookingRepo = bookingRepo;
        this.foodOrderRepo = foodOrderRepo;
        this.foodOrderItemRepo = foodOrderItemRepo;

    }
    
 // ==========================
 // Generate Room Bill
 // ==========================

 public Bill generateRoomBill(UUID bookingId) {

     Booking booking = bookingRepo.findById(bookingId)

             .orElseThrow(() ->
                     new RuntimeException("Booking Not Found"));

     Bill bill = new Bill();

     bill.setRoomBooking(booking);
     bill.setBillType(BillType.ROOM);

     BigDecimal subtotal = booking.getTotalAmount();

     BigDecimal tax = subtotal.multiply(new BigDecimal("0.18"));

     BigDecimal discount = BigDecimal.ZERO;

     BigDecimal grandTotal = subtotal
             .add(tax)
             .subtract(discount);

     bill.setSubtotal(subtotal);
     bill.setTax(tax);
     bill.setDiscount(discount);
     bill.setGrandTotal(grandTotal);
     bill.setBillStatus(BillStatus.UNPAID);

     return billRepo.save(bill);

 }
 
//==========================
//Generate Food Bill
//==========================

public Bill generateFoodBill(UUID foodOrderId) {

  FoodOrder order = foodOrderRepo.findById(foodOrderId)

          .orElseThrow(() ->
                  new RuntimeException("Food Order Not Found"));

  List<FoodOrderItem> items =
          foodOrderItemRepo.findByFoodOrder(order);

  BigDecimal subtotal = BigDecimal.ZERO;

  for (FoodOrderItem item : items) {

      subtotal = subtotal.add(item.getSubtotal());

  }

  BigDecimal tax = subtotal.multiply(new BigDecimal("0.05"));

  BigDecimal discount = BigDecimal.ZERO;

  BigDecimal grandTotal = subtotal
          .add(tax)
          .subtract(discount);

  Bill bill = new Bill();

  bill.setFoodOrder(order);
  bill.setBillType(BillType.RESTAURANT);

  bill.setSubtotal(subtotal);
  bill.setTax(tax);
  bill.setDiscount(discount);
  bill.setGrandTotal(grandTotal);
  bill.setBillStatus(BillStatus.UNPAID);

  return billRepo.save(bill);

}

//==========================
//Get All Bills
//==========================

public List<Bill> getAll() {

 return billRepo.findAll();

}

//==========================
//Get Bill By Id
//==========================

public Bill getById(UUID id) {

 return billRepo.findById(id)

         .orElseThrow(() ->
                 new RuntimeException("Bill Not Found"));

}

//==========================
//Delete Bill
//==========================

public void delete(UUID id) {

 Bill bill = getById(id);

 billRepo.delete(bill);

}


}