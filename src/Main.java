import exceptions.EShopException;
import repositories.CartRepository;
import repositories.OrderRepository;
import repositories.file.FileCartRepository;
import repositories.file.FileOrderRepository;
import services.OrderService;

public class Main {

  public static void main(String[] args) {

        try {

          CartRepository cartRepository = new FileCartRepository("carts.json");
          OrderRepository orderRepository = new FileOrderRepository("orders.json");

          // 2. Inicializace Service (Předáme jí repozitáře, které potřebuje)
          OrderService orderService = new OrderService(orderRepository, cartRepository);

          ConsoleController controller = new ConsoleController(orderService);


          System.out.println("--- Welcome to the E-Shop System 2026 ---");
          controller.start();

        } catch (EShopException e) {

          System.err.println("Failed to launch application: " + e.getMessage());
        } catch (Exception e) {

          System.err.println("An unexpected system error occurred: " + e.getMessage());
          e.printStackTrace();
        }
      }
    }
    
