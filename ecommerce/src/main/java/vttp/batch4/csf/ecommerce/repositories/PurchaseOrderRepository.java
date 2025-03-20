package vttp.batch4.csf.ecommerce.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import vttp.batch4.csf.ecommerce.models.LineItem;
import vttp.batch4.csf.ecommerce.models.Order;

@Repository
public class PurchaseOrderRepository {

  @Autowired
  private JdbcTemplate template;

  // SQL statements
  private static final String SQL_INSERT_ORDER = "INSERT INTO orders(order_id, date, name, address, priority, comments) VALUES (?, ?, ?, ?, ?, ?)";

  private static final String SQL_INSERT_LINE_ITEM = "INSERT INTO line_items(order_id, product_id, name, quantity, price) VALUES (?, ?, ?, ?, ?)";

  // IMPORTANT: DO NOT MODIFY THIS METHOD.
  // If this method is changed, any assessment task relying on this method will
  // not be marked
  // You may only add Exception to the method's signature
  public void create(Order order) throws Exception {
    try {
      // Print debug information
      System.out.println("Inserting order: " + order.getOrderId());

      // Insert the order details
      template.update(SQL_INSERT_ORDER,
          order.getOrderId(),
          new java.sql.Timestamp(order.getDate().getTime()),
          order.getName(),
          order.getAddress(),
          order.isPriority() ? 1 : 0, // Convert boolean to 1/0 for MySQL
          order.getComments());

      // Check if cart is properly initialized
      if (order.getCart() == null) {
        throw new Exception("Cart is null");
      }

      if (order.getCart().getLineItems() == null) {
        throw new Exception("Line items list is null");
      }

      // Insert all the line items for this order
      for (LineItem lineItem : order.getCart().getLineItems()) {
        System.out.println("Inserting line item: " + lineItem);

        template.update(SQL_INSERT_LINE_ITEM,
            order.getOrderId(),
            lineItem.getProductId(),
            lineItem.getName(),
            lineItem.getQuantity(),
            lineItem.getPrice());
      }

      System.out.println("Order and line items inserted successfully");
    } catch (Exception ex) {
      ex.printStackTrace();
      throw new Exception("Error inserting order into database: " + ex.getMessage(), ex);
    }
  }
}