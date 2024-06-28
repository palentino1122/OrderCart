package com.placeordersystem.order.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.placeordersystem.order.dto.Order;
import com.placeordersystem.order.dto.Response;
import com.placeordersystem.order.model.OrderItem;
import com.placeordersystem.order.model.Product;
import com.placeordersystem.order.repository.OrderItemRepository;


@Service
public class OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private ProductService productService;

    public OrderItem order(Order order) {
        Product product = productService.findById(order.getProductId());
        if (product == null) {
            return null;
        }
        OrderItem orderItem = new OrderItem();
        orderItem.setCustomerName(order.getCustomerName());
        orderItem.setAddress(order.getAddress());
        orderItem.setProductId(order.getProductId());
        orderItem.setQuantity(order.getQuantity());
        orderItem.setTotal(order.getQuantity() * product.getPrice());
        orderItem.setStatus("order");
        return orderItemRepository.save(orderItem);
    }

    public Response getOrderItem(String customerName, String address, String status) {

        Response response = new Response();
        try {
            List<OrderItem> orderCarts = orderItemRepository.findByCustomerNameAndStatus(customerName, status);
            double grandTotal = orderItemRepository.sumTotalByCustomerNameAndStatus(customerName, status);
            response.setNamaCustomer(customerName);
            response.setAlamat(address);
            response.setOrderChart(orderCarts);
            response.setGrandTotal(grandTotal);
        } catch (Exception e) {
            return null;
        }

        return response;
    }

    public Response saveOrder(String customerName, String status) {
        List<OrderItem> orderCarts = orderItemRepository.findByCustomerNameAndStatus(customerName, status);
        if (orderCarts.isEmpty()) {
            return null;

        }
        double grandTotal = orderItemRepository.sumTotalByCustomerNameAndStatus(customerName, status);
        Response response = new Response();
        orderCarts.forEach(item -> {
            item.setStatus("paid");
            orderItemRepository.save(item);
        });
        response.setGrandTotal(grandTotal);
        response.setNamaCustomer(customerName);
        response.setOrderChart(orderCarts);

        return response;
    }
}
