package repositories.file;

import com.google.gson.reflect.TypeToken;
import model.CartItem;
import repositories.CartItemRepository;

import java.util.List;

public class FileCartItemRepository extends AbstractFileRepository<CartItem> implements CartItemRepository {
  public FileCartItemRepository(String filePath) {
    super(filePath, new TypeToken<List<CartItem>>(){}.getType());
  }

  @Override
  protected Long getEntityId(CartItem entity) { return entity.getId(); }

  @Override
  public CartItem findById(long id) {
    return loadAll().stream().filter(ci -> ci.getId() == id).findFirst().orElse(null);
  }

  @Override
  public List<CartItem> findByCartId(long cartId) {
    return loadAll();
  }
}
