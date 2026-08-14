import exceptions.BusinessLogicException;
import exceptions.EShopException;
import exceptions.EntityNotFoundException;
import model.Cart;
import model.Order;
import services.OrderService;

import java.util.List;
import java.util.Scanner;

public class ConsoleController {

  private final OrderService orderService;

  private final Scanner scanner = new Scanner(System.in);

  public ConsoleController(OrderService orderService) {
    this.orderService = orderService;
  }

  public void start() {
    while (true) {
      System.out.println("\n=== Welcome To E-Shop ===");
      System.out.println("1. Create a new Cart");
      System.out.println("2. List all Carts");
      System.out.println("3. Show Cart detail (by ID)");
      System.out.println("4. List all Orders");
      System.out.println("5. Show Order detail (by ID)");
      System.out.println("6. CHECKOUT (Cart -> Order)");
      System.out.println("0. Exit");
      System.out.print("Select an option: ");

      String choice = scanner.nextLine();

      switch (choice) {
        case "1" -> handleCreateCart();
        case "2" -> listAllCarts();
        case "3" -> showCartDetail();
        case "4" -> listAllOrders();
        case "5" -> showOrderDetail();
        case "6" -> handleCheckout();
        case "0" -> {
          System.out.println("Exiting the program... Goodbye!");
          return;
        }
        default -> System.out.println("Invalid choice, try again.");
      }
    }
  }
  private void handleCreateCart () {

    Cart newCart = new Cart();
    orderService.createNewCart(newCart);
    System.out.println("New empty cart created with ID: " + newCart.getId());
  }

  private void listAllCarts () {
    List<Cart> carts = orderService.getAllFromCart();
    if (carts.isEmpty()) {
      System.out.println("No carts found.");
    } else {
      carts.forEach(c -> System.out.println("Cart ID: " + c.getId() + " | Items: " + c.getItems().size()));
    }
  }

  private void showCartDetail () {
    System.out.print("Enter Cart ID: ");
    try {
      long id = Long.parseLong(scanner.nextLine());
      Cart cart = orderService.getCartById(id);
      if (cart != null) {
        System.out.println(cart);
      } else {
        System.out.println("Cart not found.");
      }
    } catch (NumberFormatException e) {
      System.out.println("Error: Please enter a valid number.");
    }
  }

  private void listAllOrders () {
    List<Order> orders = orderService.getAllOrder();
    if (orders.isEmpty()) {
      System.out.println("No order found.");
    }
    else {
      orders.forEach(c -> System.out.println("Order ID: " + c.getId() + " | Status: " + " | Price: " + c.getTotalPrice()));
    }

  }
  private void showOrderDetail () {
    System.out.print("Enter Order ID: ");
    try {
      long id = Long.parseLong(scanner.nextLine());
      Order order = orderService.getOrderById(id);
      if (order != null) {
        System.out.println(order);
      } else {
        System.out.println("Order not found.");
      }
    } catch (NumberFormatException e) {
      System.out.println("Error: Please enter a valid number.");
    }
  }
  private void handleCheckout () {
    System.out.print("Enter Cart ID to checkout: ");
    try {
      long cartId = Long.parseLong(scanner.nextLine());
      Order newOrder = orderService.checkout(cartId);
      System.out.println("Checkout successful! Order #" + newOrder.getId() + " has been created.");
    }  catch (NumberFormatException e) {
    System.out.println("Error: Invalid ID format. Please enter a number.");
  } catch (EntityNotFoundException e) {
    // Hláška, když košík v JSONu prostě není
    System.out.println("Not Found: " + e.getMessage());
  } catch (BusinessLogicException e) {
    // Hláška, když je košík prázdný nebo nesplňuje pravidla
    System.out.println("Validation Error: " + e.getMessage());
  } catch (EShopException e) {
    // Obecná chyba tvé aplikace
    System.out.println("Application Error: " + e.getMessage());
  } catch (Exception e) {
    // Totální selhání (např. chyba disku nebo Gsonu)
    System.out.println("System Failure: An unexpected error occurred.");
    }
  }
}
