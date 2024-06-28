package com.placeordersystem.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<OrderItem> postMethodName(@RequestBody Order item) {
        OrderItem orderItem = oderItemService.order(item);
        if (orderItem == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(orderItem);
    }

    @GetMapping()
    public ResponseEntity<Response> getMethodName(@RequestBody Request item) {
        Response order = oderItemService.getOrderItem(item.getCustomerName(), item.getAddress(), item.getStatus());
        if (order == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(order);
    }

    @PostMapping()
    public ResponseEntity<Response> paidOrder(@RequestBody Request item) {
        Response order = oderItemService.saveOrder(item.getCustomerName(), item.getStatus());
        if (order == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(order);
    }

}
