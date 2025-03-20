package vttp.batch4.csf.ecommerce;


import org.bson.Document;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import vttp.batch4.csf.ecommerce.models.Product;

public class Utils {

  // IMPORTANT: DO NOT MODIFY THIS METHOD.
  // If this method is changed, any assessment task relying on this method will
  // not be marked
  public static Product toProduct(Document doc) {
    Product product = new Product();
    product.setId(doc.getObjectId("_id").toHexString());
    product.setName(doc.getString("ProductName"));
    product.setBrand(doc.getString("Brand"));
    product.setPrice(doc.getDouble("Price").floatValue());
    product.setDiscountPrice(doc.getDouble("DiscountPrice").floatValue());
    product.setImage(doc.getString("Image_Url"));
    product.setQuantity(doc.getString("Quantity"));
    return product;
  }

  // IMPORTANT: DO NOT MODIFY THIS METHOD.
  // If this method is changed, any assessment task relying on this method will
  // not be marked
  public static JsonObject toJson(Product product) {
    return Json.createObjectBuilder()
      .add("prodId", product.getId())
      .add("name", product.getName())
      .add("brand", product.getBrand())
      .add("price", product.getPrice())
      .add("discountPrice", product.getDiscountPrice())
      .add("image", product.getImage())
      .add("quantity", product.getQuantity())
      .build();
  }
}
