package com.placeordersystem.order.dto;

import lombok.Data;

@Data
public class Request {

    public String customerName;
    public String address;
    public String status;
}
