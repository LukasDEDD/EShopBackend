package com.repositories;

import com.entity.OrderItem;

import java.util.List;

public interface OrderItemRepository extends Repository<OrderItem> {
  List<OrderItem> findByOrderId(long orderId);
}

