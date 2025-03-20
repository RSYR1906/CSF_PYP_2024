package vttp.batch4.csf.ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vttp.batch4.csf.ecommerce.models.LineItem;
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
  @Transactional(rollbackFor = Exception.class)
  public void createNewPurchaseOrder(Order order) throws Exception {
    // Validate the order before attempting any database operations
    validateOrder(order);

    try {
      // All validation passed, now save the order to the database
      poRepo.create(order);
    } catch (Exception ex) {
      // Log the exception for debugging
      System.err.println("Error creating purchase order: " + ex.getMessage());
      ex.printStackTrace();

      // Rethrow the exception to trigger transaction rollback
      throw new Exception("Error creating purchase order: " + ex.getMessage(), ex);
    }
  }

  /**
   * Validates all aspects of an order before processing.
   * This separates validation logic from database operations.
   */
  private void validateOrder(Order order) throws Exception {
    // Check customer details
    if (order.getName() == null || order.getName().trim().isEmpty()) {
      throw new Exception("Customer name is required");
    }

    if (order.getAddress() == null || order.getAddress().trim().isEmpty()) {
      throw new Exception("Delivery address is required");
    }

    // Check cart and line items
    if (order.getCart() == null || order.getCart().getLineItems() == null) {
      throw new Exception("Cart cannot be empty");
    }

    if (order.getCart().getLineItems().isEmpty()) {
      throw new Exception("Cart cannot be empty");
    }

    // Validate each line item
    for (LineItem item : order.getCart().getLineItems()) {
      if (item.getProductId() == null || item.getProductId().trim().isEmpty()) {
        throw new Exception("Product ID is required for all items");
      }

      if (item.getName() == null || item.getName().trim().isEmpty()) {
        throw new Exception("Product name is required for all items");
      }

      if (item.getQuantity() <= 0) {
        throw new Exception("Quantity must be greater than zero for all items");
      }
    }
  }
}