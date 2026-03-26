package services;

import exceptions.BusinessLogicException;
import exceptions.EntityNotFoundException;
import model.Cart;
import model.Order;
import repositories.CartRepository;
import repositories.OrderRepository;

import java.util.List;

public class OrderService {

  private final OrderRepository orderRepository;
  private final CartRepository cartRepository;

  public OrderService(OrderRepository orderRepository, CartRepository cartRepository) {
    this.orderRepository = orderRepository;
    this.cartRepository = cartRepository;
  }

  public List<Order> getAllOrder() {

    return orderRepository.findAll();
  }

  public List<Cart> getAllFromCart() {

    return cartRepository.findAll();
  }

  public Order getOrderById(long id) {

    return orderRepository.findById(id);
  }

  public Cart getCartById(long id) {
    Cart cart = cartRepository.findById(id);

    if (cart == null) {

      throw new EntityNotFoundException("Košík s ID " + id + " neexistuje!");
    }

    return cart;
  }

  public void createNewOrder(Order order) {
    List<Order> allOrder = orderRepository.findAll();
    long nextId = allOrder.stream()
      .mapToLong(Order::getId)
      .max()
      .orElse(0) + 1;

    order.setId(nextId);
    orderRepository.save(order);
  }

  public void createNewCart(Cart cart) {
    List<Cart> allCart = cartRepository.findAll();
    long nextId = allCart.stream()
      .mapToLong(Cart::getId)
      .max()
      .orElse(0) + 1;

    cart.setId(nextId);
    cartRepository.save(cart);
  }
  public Order checkout(long cartId) {

    Cart cart = cartRepository.findById(cartId);

    if (cart == null) {
      throw new EntityNotFoundException("Cart with ID " + cartId + " was not found!");
    }

    if (cart.getItems().isEmpty()) {
      throw new BusinessLogicException("Cannot checkout an empty cart!");
    }

    Order order = new Order();
    order.setUser(cart.getUser());

    order.setTotalPrice(cart.getTotalPrice());
    order.setCreatedAt(java.time.LocalDateTime.now());
    order.setStatus(model.OrderStatus.CREATED);

    createNewOrder(order);

     cart.getItems().clear();
     cartRepository.save(cart);

    return order;
  }
}
