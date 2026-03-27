
import exceptions.EntityNotFoundException;
import model.Cart;
import model.CartItem;
import model.Order;
import model.OrderStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import repositories.CartRepository;
import repositories.OrderRepository;
import services.OrderService;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

public class TestOrderService {

  @Mock

  private OrderRepository orderRepository;

  @Mock
  private CartRepository cartRepository;

  @InjectMocks

  private OrderService orderService;


 @Test

void getAllOrder_returnListFromRepository() {

 List<Order> mockOrder = new ArrayList<>();
   mockOrder.add(new Order());

   when(orderRepository.findAll()).thenReturn(mockOrder);

   List<Order> result = orderService.getAllOrder();

   assertEquals(1, result.size());
   verify(orderRepository).findAll();

 }
  @Test

  void getAllFromCart_returnListFromRepository() {

    List<Cart> mockCart = new ArrayList<>();
    mockCart.add(new Cart());

    when(cartRepository.findAll()).thenReturn(mockCart);

    List<Cart> result = orderService.getAllFromCart();

    assertEquals(1, result.size());
    verify(cartRepository).findAll();

  }

  @Test
  void getOrderById_ShouldReturnOrder_WhenOrderExists() {

    long orderId = 1L;
    Order mockOrder = new Order();
    mockOrder.setId(orderId);

    when(orderRepository.findById(orderId)).thenReturn(mockOrder);;

    Order result = orderService.getOrderById(orderId);

    assertNotNull(result);
    assertEquals(orderId, result.getId());
    verify(orderRepository, times(1)).findById(orderId);
  }

  @Test
  void getOrderById_ShouldReturnEmpty_WhenOrderDoesNotExist() {

    long orderId = 99L;
    when(orderRepository.findById(orderId)).thenReturn(null);


    Order result = orderService.getOrderById(orderId);


    assertNull(result);
  }
  @Test
  void getCartById_ShouldReturnOrder_WhenCartExists() {

    long cartId = 1L;
    Cart mockCart = new Cart();
    mockCart.setId(cartId);

    when(cartRepository.findById(cartId)).thenReturn(mockCart);


    Cart result = orderService.getCartById(cartId);


    assertNotNull(result);
    assertEquals(cartId, result.getId());
    verify(cartRepository, times(1)).findById(cartId);
  }

  @Test
  void getCartById_ShouldReturnEmpty_WhenCartDoesNotExist() {

    long cartId = 99L;
    when(cartRepository.findById(cartId)).thenReturn(null);

    assertThrows(EntityNotFoundException.class, () -> {
      orderService.getCartById(cartId);
    });
  }
  @Test

  void createNewCart_savesNewCartWithIncrementedId () {

    Cart existingCart = new Cart();
    existingCart.setId(1L);

    List<Cart> allCarts = new ArrayList<>();
    allCarts.add(existingCart);


    when(cartRepository.findAll()).thenReturn(allCarts);


    Cart newCart = new Cart();


  orderService.createNewCart(newCart);

  assertEquals(2L, newCart.getId());

  verify(cartRepository).save(newCart);
  }

  @Test
  void createNewOrder_savesNewOrderWithIncrementedId() {

    Order existingOrder = new Order();
    existingOrder.setId(1L);

    List<Order> allOrders = new ArrayList<>();
    allOrders.add(existingOrder);


    when(orderRepository.findAll()).thenReturn(allOrders);

    Order newOrder = new Order();


    orderService.createNewOrder(newOrder);


    assertEquals(2L, newOrder.getId());


    verify(orderRepository).save(newOrder);
  }

  @Test
  void checkout_ShouldCreateOrderAndClearCart_WhenCartIsValid() {

    long cartId = 1L;

    Cart mockCart = new Cart();
    mockCart.setId(cartId);
    mockCart.setQuantity(1);
    mockCart.setUnitPrice(new BigDecimal("1500.0"));


    List<CartItem> items = new ArrayList<>();
    items.add(new CartItem());
    mockCart.setItems(items);

    when(cartRepository.findById(cartId)).thenReturn(mockCart);
    when(orderRepository.findAll()).thenReturn(new ArrayList<>());

    Order result = orderService.checkout(cartId);


    assertNotNull(result);

    assertEquals(model.OrderStatus.CREATED, result.getStatus());
    assertTrue(mockCart.getItems().isEmpty(), "The Cart must be empty");

    verify(orderRepository).save(any(Order.class));
    verify(cartRepository).save(mockCart);
  }
}



