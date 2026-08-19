package com.services;

import com.entity.Cart;
import com.entity.Order;
import com.entity.OrderStatus;
import com.exceptions.BusinessLogicException;
import com.exceptions.EntityNotFoundException;
import com.repositories.jpa.CartJpaRepository;
import com.repositories.jpa.OrderJpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

  private final OrderJpaRepository orderJpaRepository;
  private final CartJpaRepository cartJpaRepository;

  public OrderService(OrderJpaRepository orderJpaRepository,
                      CartJpaRepository cartJpaRepository) {
    this.orderJpaRepository = orderJpaRepository;
    this.cartJpaRepository = cartJpaRepository;
  }

  public List<Order> getAllOrders() {
    return orderJpaRepository.findAll();
  }

  public List<Cart> getAllCarts() {
    return cartJpaRepository.findAll();
  }

  public Order getOrderById(Long id) {
    return orderJpaRepository.findById(id)
      .orElseThrow(() ->
        new EntityNotFoundException(
          "Order with ID " + id + " was not found!"
        )
      );
  }

  public Cart getCartById(Long id) {
    return cartJpaRepository.findById(id)
      .orElseThrow(() ->
        new EntityNotFoundException(
          "Cart with ID " + id + " was not found!"
        )
      );
  }

  public Order createNewOrder(Order order) {
    return orderJpaRepository.save(order);
  }

  public Cart createNewCart(Cart cart) {
    return cartJpaRepository.save(cart);
  }

  @Transactional
  public Order checkout(Long cartId) {

    Cart cart = cartJpaRepository.findById(cartId)
      .orElseThrow(() ->
        new EntityNotFoundException(
          "Cart with ID " + cartId + " was not found!"
        )
      );

    if (cart.getItems() == null || cart.getItems().isEmpty()) {
      throw new BusinessLogicException(
        "Cannot checkout an empty cart!"
      );
    }

    Order order = new Order();

    order.setUser(cart.getUser());
    order.setTotalPrice(cart.getTotalPrice());
    order.setCreatedAt(LocalDateTime.now());
    order.setStatus(OrderStatus.CREATED);

    Order savedOrder = orderJpaRepository.save(order);

    cart.getItems().clear();
    cart.setTotalPrice(BigDecimal.ZERO);
    cart.setUpdatedAt(LocalDateTime.now());

    cartJpaRepository.save(cart);

    return savedOrder;
  }
}