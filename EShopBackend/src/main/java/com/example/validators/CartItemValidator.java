package com.example.validators;

import com.example.entity.CartItem;

public class CartItemValidator {

  public static void validate(CartItem item) {
    if (item.getQuantity() == null || item.getQuantity() <= 0) {
      throw new IllegalArgumentException("Quantity must be > 0");
    }
  }
}
