package com.bridgelabz.bookstore.order.controller;

import com.bridgelabz.bookstore.cart.services.CartServiceImpl;
import com.bridgelabz.bookstore.order.dto.OrderDTO;
import com.bridgelabz.bookstore.order.entity.OrderEntity;
import com.bridgelabz.bookstore.order.service.IOrderService;
import com.bridgelabz.bookstore.order.service.OrderService;
import com.bridgelabz.bookstore.user.dto.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @PostMapping("/placeOrder/{jwt}")
    public ResponseEntity<ResponseDTO> placeOrder(@PathVariable String jwt, @RequestBody OrderDTO orderDTO){
        OrderEntity orderEntity = orderService.placeOrder(jwt, orderDTO);
        ResponseDTO responseDTO = new ResponseDTO("Order Placed", orderEntity.getOrderId());
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/cancelOrder/{userId}/{orderId}")
    public ResponseEntity<ResponseDTO> cancelOrder(@PathVariable int orderId, @PathVariable int userId){

        ResponseDTO responseDTO = new ResponseDTO("Status", orderService.cancelOrder(orderId,userId));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    @GetMapping("/userOrder/{userId}")
    public ResponseEntity<ResponseDTO> getUserOrders(@PathVariable int userId){
        List<OrderEntity> userOrders = orderService.getUserOrders(userId);
        ResponseDTO responseDTO = new ResponseDTO("Order Placed successfully", userOrders);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }



}
