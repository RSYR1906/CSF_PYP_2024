package vttp.batch4.csf.ecommerce.models;

import java.util.LinkedList;
import java.util.List;

// IMPORTANT: DO NOT MODIFY THIS CLASS
// If this class is changed, any assessment task relying on this class will
// not be marked
public class Cart {
  private List<LineItem> lineItems = new LinkedList<>();

  public void setLineItems(List<LineItem> lineItems) { this.lineItems = lineItems; }
  public List<LineItem> getLineItems() { return this.lineItems; }

  public void addLineItem(LineItem lineItem) { this.lineItems.add(lineItem); }

  @Override
  public String toString() {
    return "Cart{lineItems=%s}".formatted(lineItems);
  }
}
