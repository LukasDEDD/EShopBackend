package repositories;

import model.Order;

import java.util.List;

public interface OrderRepository extends Repository<Order> {
  List<Order> findByUserId(long userId);
}

