package repositories.file;

import com.google.gson.reflect.TypeToken;
import model.Order;
import repositories.OrderRepository;

import java.util.List;
import java.util.stream.Collectors;

public class FileOrderRepository extends AbstractFileRepository<Order> implements OrderRepository {

  public FileOrderRepository(String filePath) {
    super(filePath, new TypeToken<List<Order>>(){}.getType());
  }

  @Override
  protected Long getEntityId(Order entity) { return entity.getId(); }

  @Override
  public Order findById(long id) {
    return loadAll().stream().filter(o -> o.getId() == id).findFirst().orElse(null);
  }

  @Override
  public List<Order> findByUserId(long userId) {
    return loadAll().stream()
      .filter(o -> o.getUser() != null && o.getUser().getId() == userId)
      .collect(Collectors.toList());
  }
}
