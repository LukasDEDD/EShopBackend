package model;

import java.math.BigDecimal;
import java.util.Objects;

public class OrderItem {

  Long id;
  String productName;
  BigDecimal unitPrice;
  Integer quantity;
  BigDecimal totalPrice;

  public OrderItem() {
  }

  public OrderItem(BigDecimal totalPrice, Integer quantity, BigDecimal unitPrice, String productName, Long id) {
    this.totalPrice = totalPrice;
    this.quantity = quantity;
    this.unitPrice = unitPrice;
    this.productName = productName;
    this.id = id;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public BigDecimal getUnitPrice() {
    return unitPrice;
  }

  public void setUnitPrice(BigDecimal unitPrice) {
    this.unitPrice = unitPrice;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public BigDecimal getTotalPrice() {
    return totalPrice;
  }

  public void setTotalPrice(BigDecimal totalPrice) {
    this.totalPrice = totalPrice;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof User)) return false;
    User user = (User) o;
    return id != null && id.equals(user.id);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }

  @Override
  public String toString() {
    return "OrderItem{" +
      "id=" + id +
      ", productName='" + productName + '\'' +
      ", unitPrice=" + unitPrice +
      ", quantity=" + quantity +
      ", totalPrice=" + totalPrice +
      '}';
  }
}
