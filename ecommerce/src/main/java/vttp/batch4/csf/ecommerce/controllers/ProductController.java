package vttp.batch4.csf.ecommerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import vttp.batch4.csf.ecommerce.Utils;
import vttp.batch4.csf.ecommerce.services.ProductService;

// IMPORTANT: DO NOT MODIFY THIS CLASS
// If this class is changed, any assessment task relying on this class will
// not be marked
@Controller
@RequestMapping(path="/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {

  @Autowired
  private ProductService prodSvc;

  @GetMapping(path="/categories")
  @ResponseBody
  public ResponseEntity<String> getCategories() {

    JsonArrayBuilder arrBuilder = Json.createArrayBuilder(prodSvc.getProductCategories());

    return ResponseEntity.ok(arrBuilder.build().toString());
  }

  @GetMapping(path="/category/{category}")
  @ResponseBody
  public ResponseEntity<String> getCategory(@PathVariable String category) {

    List<JsonObject> products = prodSvc.getProductByCategory(category).stream()
        .map(Utils::toJson)
        .toList();

    return ResponseEntity.ok(Json.createArrayBuilder(products).build().toString());
  }
}
