package vttp.batch4.csf.ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import vttp.batch4.csf.ecommerce.models.LineItem;
import vttp.batch4.csf.ecommerce.models.Order;
import vttp.batch4.csf.ecommerce.services.PurchaseOrderService;

@Controller
@RequestMapping(path = "/api")
public class OrderController {

  @Autowired
  private PurchaseOrderService poSvc;

  // IMPORTANT: DO NOT MODIFY THIS METHOD.
  // If this method is changed, any assessment task relying on this method will
  // not be marked
  @PostMapping(path = "/order", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public ResponseEntity<String> postOrder(@RequestBody Order order) {
    try {
      System.out.println("Received order: " + order);

      // Validate order data before processing
      if (order.getName() == null || order.getName().isEmpty()) {
        return ResponseEntity.badRequest().body(
            Json.createObjectBuilder()
                .add("error", "Customer name is required")
                .build().toString());
      }

      if (order.getAddress() == null || order.getAddress().isEmpty()) {
        return ResponseEntity.badRequest().body(
            Json.createObjectBuilder()
                .add("error", "Delivery address is required")
                .build().toString());
      }

      if (order.getCart() == null || order.getCart().getLineItems() == null
          || order.getCart().getLineItems().isEmpty()) {
        return ResponseEntity.badRequest().body(
            Json.createObjectBuilder()
                .add("error", "Cart cannot be empty")
                .build().toString());
      }

      // Debug info about line items
      System.out.println("Line items count: " + order.getCart().getLineItems().size());
      for (LineItem item : order.getCart().getLineItems()) {
        System.out.println("Line item: " + item);
      }

      // Process the order
      poSvc.createNewPurchaseOrder(order);

      // Return success response
      JsonObject resp = Json.createObjectBuilder()
          .add("orderId", order.getOrderId())
          .add("status", "success")
          .build();

      return ResponseEntity.status(HttpStatus.CREATED).body(resp.toString());
    } catch (Exception ex) {
      ex.printStackTrace();

      // Return detailed error information
      JsonObject resp = Json.createObjectBuilder()
          .add("error", "Error processing order: " + ex.getMessage())
          .add("details", ex.getClass().getName())
          .build();

      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp.toString());
    }
  }
}