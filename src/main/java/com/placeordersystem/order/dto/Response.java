package com.placeordersystem.order.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.placeordersystem.order.model.OrderItem;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {
    private String namaCustomer;
    private String alamat;
    List<OrderItem> orderChart;
    private double grandTotal;
}
