package repositories.file;

import com.google.gson.reflect.TypeToken;
import model.Cart;
import model.CartItem;
import repositories.CartRepository;

import java.util.List;
import java.util.stream.Collectors;

public class FileCartRepository extends AbstractFileRepository<Cart> implements CartRepository {
  public FileCartRepository(String filePath) {
    super(filePath, new TypeToken<List<Cart>>(){}.getType());
  }

  @Override
  protected Long getEntityId(Cart entity) { return entity.getId(); }

  @Override
  public Cart findById(long id) {
    return loadAll().stream().filter(c -> c.getId() == id).findFirst().orElse(null);
  }

  @Override
  public Cart findByUserId(long userId) {
    return loadAll().stream()
      .filter(c -> c.getUser() != null && c.getUser().getId() == userId)
      .findFirst().orElse(null);
  }
}
