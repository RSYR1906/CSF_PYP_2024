package vttp.batch4.csf.ecommerce.models;

// IMPORTANT: DO NOT MODIFY THIS CLASS
// If this class is changed, any assessment task relying on this class will
// not be marked
public class LineItem {
  private String productId;
  private String name;
  private int quantity;
  private float price;

  public void setProductId(String productId) { this.productId = productId; }
  public String getProductId() { return this.productId; }

  public void setName(String name) { this.name = name; }
  public String getName() { return this.name; }

  public void setQuantity(int quantity) { this.quantity =   quantity; }
  public int getQuantity() { return this.quantity; }

  public void setPrice(float price) { this.price = price; }
  public float getPrice() { return this.price; }

  @Override
  public String toString() {
    return "LineItem{productId=%s, name=%s, quantity=%d, price=%.3f}"
      .formatted(productId, name, quantity, price);
  }
}
