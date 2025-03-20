package vttp.batch4.csf.ecommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp.batch4.csf.ecommerce.models.Product;
import vttp.batch4.csf.ecommerce.repositories.ProductsRepository;

// IMPORTANT: DO NOT MODIFY THIS CLASS
// If this class is changed, any assessment task relying on this class will
// not be marked

@Service
public class ProductService {

  @Autowired
  private ProductsRepository prodRepo;

  public List<String> getProductCategories() {
    return prodRepo.getProductCategories();
  }

  public List<Product> getProductByCategory(String category) {
    return prodRepo.getProductByCategory(category, 20);
  }
}
