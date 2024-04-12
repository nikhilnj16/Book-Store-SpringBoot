package com.bridgelabz.bookstore.order.service;

import com.bridgelabz.bookstore.order.dto.OrderDTO;
import com.bridgelabz.bookstore.order.entity.OrderEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface IOrderService {
    OrderEntity placeOrder(@PathVariable String jwt, @RequestBody OrderDTO orderDTO);

    List<OrderEntity> getUserOrders(int userId);

    String cancelOrder(int orderId, int userId);

}
