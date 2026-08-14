package repositories;

import java.util.List;


public interface Repository<T> {
  T findById(long id);
  List<T> findAll();
  void save(T entity);
}

