package com.placeordersystem.order.dto;

import lombok.Data;

@Data
public class OrderCart {
    public String customerName;
    public String address;
    public String productName;
    public String type;
    public double price;
    public int quantity;
    public double total;
}