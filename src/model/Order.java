package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Order {

  Long id;
  User user;
  List<OrderItem> items;
  OrderStatus status;
  BigDecimal totalPrice;
  LocalDateTime createdAt;

  public Order() {
  }

  public Order(Long id, User user, List<OrderItem> items, OrderStatus status, BigDecimal totalPrice, LocalDateTime createdAt) {
    this.id = id;
    this.user = user;
    this.items = items;
    this.status = status;
    this.totalPrice = totalPrice;
    this.createdAt = createdAt;
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

  public List<OrderItem> getItems() {
    return items;
  }

  public void setItems(List<OrderItem> items) {
    this.items = items;
  }

  public OrderStatus getStatus() {
    return status;
  }

  public void setStatus(OrderStatus status) {
    this.status = status;
  }

  public BigDecimal getTotalPrice() {
    return totalPrice;
  }

  public void setTotalPrice(BigDecimal totalPrice) {
    this.totalPrice = totalPrice;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
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
    return "Order{" +
      "id=" + id +
      ", user=" + user +
      ", items=" + items +
      ", status=" + status +
      ", totalPrice=" + totalPrice +
      ", createdAt=" + createdAt +
      '}';
  }
}
