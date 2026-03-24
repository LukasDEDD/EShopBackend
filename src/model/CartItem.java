package model;

import java.math.BigDecimal;
import java.util.Objects;

public class CartItem {

  Long id;
  Long cartId;
  Product product;
  Integer quantity;
  BigDecimal unitPrice;
  BigDecimal totalPrice;

  public CartItem() {
  }

  public CartItem(Long id, Long cartId, Product product, Integer quantity, BigDecimal unitPrice, BigDecimal totalPrice) {
    this.id = id;
    this.cartId = cartId;
    this.product = product;
    this.quantity = quantity;
    this.unitPrice = unitPrice;
    this.totalPrice = totalPrice;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
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

  public BigDecimal getTotalPrice() {

    return unitPrice.multiply(BigDecimal.valueOf(quantity));
  }

  public void setTotalPrice(BigDecimal totalPrice) {
    this.totalPrice = totalPrice;
  }
  public Long getCartId() {
    return cartId;
  }

  public void setCartId(Long cartId) {
    this.cartId = cartId;
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
    return "CartItem{" +
      "id=" + id +
      ", product=" + product +
      ", quantity=" + quantity +
      ", unitPrice=" + unitPrice +
      ", totalPrice=" + totalPrice +
      '}';
  }
}
