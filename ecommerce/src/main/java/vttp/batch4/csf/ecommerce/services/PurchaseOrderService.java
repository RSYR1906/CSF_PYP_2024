package vttp.batch4.csf.ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vttp.batch4.csf.ecommerce.models.Order;
import vttp.batch4.csf.ecommerce.repositories.PurchaseOrderRepository;

@Service
public class PurchaseOrderService {

  @Autowired
  private PurchaseOrderRepository poRepo;

  // IMPORTANT: DO NOT MODIFY THIS METHOD.
  // If this method is changed, any assessment task relying on this method will
  // not be marked
  // You may only add Exception to the method's signature
  @Transactional
  public void createNewPurchaseOrder(Order order) throws Exception {
    try {
      // Validate the order
      if (order.getName() == null || order.getName().trim().isEmpty()) {
        throw new Exception("Customer name is required");
      }

      if (order.getAddress() == null || order.getAddress().trim().isEmpty()) {
        throw new Exception("Delivery address is required");
      }

      if (order.getCart() == null || order.getCart().getLineItems().isEmpty()) {
        throw new Exception("Cart cannot be empty");
      }
      // Save the order to the database
      poRepo.create(order);
    } catch (Exception ex) {
      // Wrap and rethrow the exception
      throw new Exception("Error creating purchase order: " + ex.getMessage(), ex);
    }
  }
}