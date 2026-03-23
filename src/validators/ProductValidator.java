package validators;

import model.Product;

import java.math.BigDecimal;

public class ProductValidator {

  public static void validate(Product product) {
    if (product.getPrice() == null ||
      product.getPrice().compareTo(BigDecimal.ZERO) < 0) {
      throw new IllegalArgumentException("Price must be >= 0");
    }
  }
}
