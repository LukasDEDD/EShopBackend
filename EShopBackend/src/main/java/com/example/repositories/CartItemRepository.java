package com.example.repositories;

import com.example.entity.CartItem;

import java.util.List;

public interface CartItemRepository extends Repository<CartItem> {
  List<CartItem> findByCartId(long cartId);
}

