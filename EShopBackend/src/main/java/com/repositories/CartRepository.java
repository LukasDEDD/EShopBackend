package com.repositories;

import com.entity.Cart;

public interface CartRepository extends Repository<Cart> {
  Cart findByUserId(long userId);
}

