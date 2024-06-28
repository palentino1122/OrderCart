package com.placeordersystem.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.placeordersystem.order.model.Product;
import com.placeordersystem.order.services.ProductService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/product")
public class ProducController {

    @Autowired
    private ProductService productService;

    @PostMapping("/add")
    public Product save(@RequestBody Product product) {
        return productService.save(product);
    }

    @GetMapping("/list")
    public Page<Product> listProduct(Pageable pageable) {
        return productService.getAllProduct(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id) {
        Product product = productService.findById(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        productService.findById(id);
        return ResponseEntity.ok(product);
    }

    @PutMapping("edit/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product entity) {

        Product product = productService.findById(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        product.setName(entity.getName());
        product.setType(entity.getType());
        product.setPrice(entity.getPrice());
        Product updateProduct = productService.save(product);
        return ResponseEntity.ok(updateProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        Product product = productService.findById(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        productService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
