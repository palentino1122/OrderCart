package com.placeordersystem.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.placeordersystem.order.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
