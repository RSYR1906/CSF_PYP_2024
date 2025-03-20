package vttp.batch4.csf.ecommerce.models;

// IMPORTANT: DO NOT MODIFY THIS CLASS
// If this class is changed, any assessment task relying on this class will
// not be marked
public class Product {
  private String id;
  private String name;
  private String brand;
  private float price;
  private float discountPrice;
  private String image;
  private String quantity;

  public void setId(String id) { this.id = id; }
  public String getId() { return this.id; }

  public void setName(String name) { this.name = name; }
  public String getName() { return this.name; }

  public void setBrand(String brand) { this.brand = brand; }
  public String getBrand() { return this.brand; }

  public void setPrice(float price) { this.price = price; }
  public float getPrice() { return this.price; }

  public void setDiscountPrice(float discountPrice) { this.discountPrice = discountPrice; }
  public float getDiscountPrice() { return this.discountPrice; }

  public void setImage(String image) { this.image = image; }
  public String getImage() { return this.image; }

  public void setQuantity(String quantity) { this.quantity = quantity; }
  public String getQuantity() { return this.quantity; }

  @Override
  public String toString() {
    return "Product{id=%s, name=%s, brand=%s, price=%f, discountPrice=%f, image=%s, quantity: %s}"
      .formatted(id, name, brand, price, discountPrice, image, quantity);
  }
}
