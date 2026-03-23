package repositories;

import model.CartItem;
import model.User;

import java.util.List;


public interface Repository<T> {
  T findById(long id);
  List<T> findAll();
  void save(T entity);
}

