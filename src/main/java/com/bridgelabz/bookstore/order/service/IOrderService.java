package com.bridgelabz.bookstore.order.service;

import com.bridgelabz.bookstore.order.dto.OrderDTO;
import com.bridgelabz.bookstore.order.entity.OrderEntity;

import java.util.List;

public interface IOrderService {
    OrderEntity placeOrder(int userId, OrderDTO orderDTO);

    List<OrderEntity> getUserOrders(int userId);

    String cancelOrder(int orderId, int userId);

}
