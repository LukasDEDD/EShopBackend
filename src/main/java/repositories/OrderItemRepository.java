package repositories;

import model.OrderItem;

import java.util.List;

public interface OrderItemRepository extends Repository<OrderItem> {
  List<OrderItem> findByOrderId(long orderId);
}

