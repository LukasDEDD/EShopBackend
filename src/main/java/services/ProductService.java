package services;

import model.Product;
import repositories.ProductRepository;
import java.util.List;

public class ProductService {


  private final ProductRepository productRepository;

  public ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public List<Product> getAllProducts() {
    return productRepository.findAll();
  }

  public Product getProductById(long id) {
    return productRepository.findById(id);
  }

  public void createNewProduct(Product product) {

    productRepository.save(product);
  }
}
