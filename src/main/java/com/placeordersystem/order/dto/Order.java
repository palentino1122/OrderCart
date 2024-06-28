package com.placeordersystem.order.dto;

import lombok.Data;

@Data
public class Order {
    private String customerName;
    private String address;
    private Long productId;
    private int quantity;
}
