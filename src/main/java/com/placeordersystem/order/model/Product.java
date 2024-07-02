package com.placeordersystem.order.model;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.Data;

@Entity
@Data
public class Product {

    @Id
    private String id;
    private String name;
    private String type;
    private double price;

    @PrePersist
    public void generateId() {
        this.id = "product-" + UUID.randomUUID().toString();
    }

}
