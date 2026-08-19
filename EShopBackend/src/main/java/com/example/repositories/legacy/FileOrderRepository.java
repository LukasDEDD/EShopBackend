package com.example.repositories.legacy;

import com.example.entity.Order;
import com.google.gson.reflect.TypeToken;
import com.example.repositories.OrderRepository;

import java.util.List;
import java.util.stream.Collectors;

public class FileOrderRepository
  extends AbstractFileRepository<Order>
  implements OrderRepository {

  public FileOrderRepository(String filePath) {
    super(filePath, new TypeToken<List<Order>>() {}.getType());
  }

  @Override
  protected Long getEntityId(Order entity) {
    return entity.getId();
  }

  @Override
  public Order findById(long id) {
    return loadAll().stream()
      .filter(order ->
        order.getId() != null &&
          order.getId().equals(id)
      )
      .findFirst()
      .orElse(null);
  }

  @Override
  public List<Order> findByUserId(long userId) {
    return loadAll().stream()
      .filter(order ->
        order.getUser() != null &&
          order.getUser().getId() != null &&
          order.getUser().getId().equals(userId)
      )
      .collect(Collectors.toList());
  }
}