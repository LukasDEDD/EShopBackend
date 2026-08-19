package com.example.services;

import com.example.entity.Product;
import com.example.exceptions.EntityNotFoundException;
import org.springframework.stereotype.Service;
import com.example.repositories.jpa.ProductJpaRepository;
import java.util.List;

@Service
public class ProductService {

  private final ProductJpaRepository productRepository;

  public ProductService(ProductJpaRepository ProductJpaRepository) {
    this.productRepository = ProductJpaRepository;
  }

  public List<Product> getAllProducts() {
    return productRepository.findAll();
  }

  public Product getProductById(Long id) {
    return productRepository.findById(id)
      .orElseThrow(() ->
        new EntityNotFoundException(
          "Product with ID " + id + " was not found!"
        ));
  }

  public Product createNewProduct(Product product) {
    return productRepository.save(product);
  }
}
