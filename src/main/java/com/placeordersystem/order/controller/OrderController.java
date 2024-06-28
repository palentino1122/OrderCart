package com.placeordersystem.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.placeordersystem.order.dto.ErrorResponse;
import com.placeordersystem.order.dto.Order;
import com.placeordersystem.order.dto.Request;
import com.placeordersystem.order.dto.Response;
import com.placeordersystem.order.model.OrderItem;
import com.placeordersystem.order.services.OrderItemService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderItemService oderItemService;

    @PostMapping("/cart")
    public ResponseEntity<?> orderProduct(@RequestBody Order item) {
        OrderItem orderItem = oderItemService.order(item);
        if (orderItem == null) {
            ErrorResponse errorResponse = new ErrorResponse("Product Not Found", "404");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
        return ResponseEntity.status(HttpStatus.OK).body(orderItem);
    }

    @GetMapping()
    public ResponseEntity<?> orderItem(@RequestBody Request item) {
        Response order = oderItemService.getOrderItem(item.getCustomerName(), item.getAddress(), item.getStatus());
        if (order == null) {
            ErrorResponse errorResponse = new ErrorResponse("Customer Not Found", "404");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
        return ResponseEntity.status(HttpStatus.OK).body(order);
    }

    @PostMapping()
    public ResponseEntity<?> paidOrder(@RequestBody Request item) {
        Response order = oderItemService.saveOrder(item.getCustomerName(), item.getStatus());
        if (order == null) {
            ErrorResponse errorResponse = new ErrorResponse("Customer Not Found", "404");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
        return ResponseEntity.status(HttpStatus.OK).body(order);
    }

}
