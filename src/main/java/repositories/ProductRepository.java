package repositories;

import model.Product;

import java.util.List;

public interface ProductRepository extends Repository<Product> {
  List<Product> findByCategory(String category);
}

