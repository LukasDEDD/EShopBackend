package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Cart {

  Long id;
  User user;
  List<CartItem> items;
  BigDecimal totalPrice;
  LocalDateTime updatedAt;
  Integer quantity;
  BigDecimal unitPrice;

  public Cart() {
  }

  public Cart(Long id, User user, List<CartItem> items, BigDecimal totalPrice, LocalDateTime updatedAt, Integer quantity, BigDecimal unitPrice) {
    this.id = id;
    this.user = user;
    this.items = items;
    this.totalPrice = totalPrice;
    this.updatedAt = updatedAt;
    this.quantity = quantity;
    this.unitPrice = unitPrice;
  }

  public Long getId() {

    return id;
  }

  public void setId(Long id) {

    this.id = id;
  }

  public User getUser() {

    return user;
  }

  public void setUser(User user) {

    this.user = user;
  }

  public List<CartItem> getItems() {

    return items;
  }

  public void setItems(List<CartItem> items) {

    this.items = items;
  }

  public BigDecimal getTotalPrice() {

    return unitPrice.multiply(BigDecimal.valueOf(quantity));
  }

  public void setTotalPrice(BigDecimal totalPrice) {

    this.totalPrice = totalPrice;
  }

  public LocalDateTime getUpdatedAt() {

    return updatedAt;
  }

  public void setUpdatedAt(LocalDateTime updatedAt) {

    this.updatedAt = updatedAt;
  }

  public Integer getQuantity() {

    return quantity;
  }

  public void setQuantity(Integer quantity) {

    this.quantity = quantity;
  }

  public BigDecimal getUnitPrice() {

    return unitPrice;
  }

  public void setUnitPrice(BigDecimal unitPrice) {

    this.unitPrice = unitPrice;
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
    return "Cart{" +
      "id=" + id +
      ", user=" + user +
      ", items=" + items +
      ", totalPrice=" + totalPrice +
      ", updatedAt=" + updatedAt +
      ", quantity=" + quantity +
      ", unitPrice=" + unitPrice +
      '}';
  }
}
