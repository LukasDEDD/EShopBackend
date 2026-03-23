package repositories;

import model.CartItem;

import java.util.List;

public interface CartItemRepository extends Repository<CartItem> {
  List<CartItem> findByCartId(long cartId);
}

