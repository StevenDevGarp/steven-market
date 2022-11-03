package com.stevenmarket.domain.service;

import com.stevenmarket.domain.Product;
import com.stevenmarket.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepositoy;

    public List<Product> getAll() {
        return productRepositoy.getAll();
    }

    public Optional<Product> getProduct(int productId){
        return productRepositoy.getProduct(productId);
    }

    public Optional<List<Product>> getByCategory(int categoryId) {
        return productRepositoy.getByCategory(categoryId);
    }
    public Product save(Product product){
        return productRepositoy.save(product);
    }

    public boolean delete(int productId){
        return getProduct(productId).map(product -> {
            productRepositoy.delete(productId);
            return true;
        }).orElse(false);
    }
}
