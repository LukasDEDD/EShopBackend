package com.example.repositories;

import com.example.entity.Cart;

public interface CartRepository extends Repository<Cart> {
  Cart findByUserId(long userId);
}

