package com.example.repositories;

import com.example.entity.Order;

import java.util.List;

public interface OrderRepository extends Repository<Order> {
  List<Order> findByUserId(long userId);
}

