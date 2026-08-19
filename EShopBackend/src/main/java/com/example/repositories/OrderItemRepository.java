package com.example.repositories;

import com.example.entity.OrderItem;

import java.util.List;

public interface OrderItemRepository extends Repository<OrderItem> {
  List<OrderItem> findByOrderId(long orderId);
}

