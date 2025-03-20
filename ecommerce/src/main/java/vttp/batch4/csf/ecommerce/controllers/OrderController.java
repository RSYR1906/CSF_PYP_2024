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

      // Process the order - this should be inside a transaction
      // The service layer will handle validation and DB operations
      poSvc.createNewPurchaseOrder(order);

      // If we reach here, the order was processed successfully
      JsonObject resp = Json.createObjectBuilder()
          .add("orderId", order.getOrderId())
          .add("status", "success")
          .build();

      return ResponseEntity.status(HttpStatus.CREATED).body(resp.toString());
    } catch (Exception ex) {
      ex.printStackTrace();

      // Create a detailed error response
      JsonObject resp = Json.createObjectBuilder()
          .add("error", "Error processing order: " + ex.getMessage())
          .add("details", ex.getClass().getName())
          .build();

      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp.toString());
    }
  }
}