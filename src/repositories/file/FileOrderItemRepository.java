package repositories.file;

import com.google.gson.reflect.TypeToken;
import model.OrderItem;
import repositories.OrderItemRepository;

import java.util.List;

public class FileOrderItemRepository extends AbstractFileRepository<OrderItem> implements OrderItemRepository {
  public FileOrderItemRepository(String filePath) {
    super(filePath, new TypeToken<List<OrderItem>>(){}.getType());
  }

  @Override
  protected Long getEntityId(OrderItem entity) { return entity.getId(); }

  @Override
  public OrderItem findById(long id) {
    return loadAll().stream().filter(oi -> oi.getId() == id).findFirst().orElse(null);
  }

  @Override
  public List<OrderItem> findByOrderId(long orderId) {
    return loadAll();
  }
}
