package com.repositories;

import com.entity.Product;

import java.util.List;

public interface ProductRepository extends Repository<Product> {
  List<Product> findByCategory(String category);
}

