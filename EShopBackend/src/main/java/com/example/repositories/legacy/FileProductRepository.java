package com.example.repositories.legacy;


import com.google.gson.reflect.TypeToken;
import com.example.entity.Product;
import com.example.repositories.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

public class FileProductRepository extends AbstractFileRepository<Product> implements ProductRepository {

  public FileProductRepository(String filePath) {
    super(filePath, new TypeToken<List<Product>>(){}.getType());
  }

  @Override
  protected Long getEntityId(Product entity) { return entity.getId(); }

  @Override
  public Product findById(long id) {
    return loadAll().stream().filter(p -> p.getId() == id).findFirst().orElse(null);
  }

  @Override
  public List<Product> findByCategory(String category) {
    return loadAll().stream()
      .filter(p -> p.getCategory().equalsIgnoreCase(category))
      .collect(Collectors.toList());
  }
}
