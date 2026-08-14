package repositories;

import model.Cart;

public interface CartRepository extends Repository<Cart> {
  Cart findByUserId(long userId);
}

