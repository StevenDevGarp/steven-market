package com.stevenmarket.web.controller;

import com.stevenmarket.domain.Product;
import com.stevenmarket.domain.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAll(){
        return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") int ProductId) {
        return productService.getProduct(ProductId)
                .map(product -> new ResponseEntity<>(product, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.FORBIDDEN));
    }
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Product>> getByCategory(@PathVariable("categoryId") int categoryId) {
        return productService.getByCategory(categoryId)
                .map(products -> new ResponseEntity<>(products, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.FORBIDDEN));
    }
    @PostMapping("/save")
    public ResponseEntity<Product> save(@RequestBody Product product){

        return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") int productId){

                if (productService.delete(productId)){
                    return new ResponseEntity(HttpStatus.OK);
                } else {
                    return new ResponseEntity((HttpStatus.FORBIDDEN));
                }
    }
}
