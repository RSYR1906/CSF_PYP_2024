package vttp.batch4.csf.ecommerce.models;

import java.util.Date;

import com.github.f4b6a3.ulid.UlidCreator;

// IMPORTANT: DO NOT MODIFY THIS CLASS
// If this class is changed, any assessment task relying on this class will
// not be marked
public class Order {
  private final String orderId;
  private Date date = new Date();
  private String name;
  private String address;
  private boolean priority;
  private String comments;
  private Cart cart = new Cart();

  public Order() {
    // 26 characters
    // Eg - 01HNS6YMJNZX24G4YN38AGBZEE
    orderId = UlidCreator.getMonotonicUlid().toString();
  }

  public String getOrderId() { return this.orderId; }

  public void setDate(Date date) { this.date = date; }
  public Date getDate() { return this.date; }

  public void setName(String name) { this.name = name; }
  public String getName() { return this.name; }

  public void setAddress(String address) { this.address = address; }
  public String getAddress() { return this.address; }

  public void setPriority(boolean priority) { this.priority = priority; }
  public boolean getPriority() { return this.priority; }
  public boolean isPriority() { return this.priority; }

  public void setComments(String comments) { this.comments = comments; }
  public String getComments() { return this.comments; }

  public void setCart(Cart cart) { this.cart = cart; }
  public Cart getCart() { return this.cart; }

  @Override
  public String toString() {
    return "Order{orderId=%s, date=%s, name=%s, address=%s, priority=%b, comments=%s, cart=%s}"
      .formatted(orderId, date, name, address, priority, comments, cart);
  }
}
