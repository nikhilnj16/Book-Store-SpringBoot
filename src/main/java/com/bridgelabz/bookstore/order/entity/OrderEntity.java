package com.bridgelabz.bookstore.order.entity;

import com.bridgelabz.bookstore.book.entity.BookEntity;
import com.bridgelabz.bookstore.cart.entity.CartEntity;
import com.bridgelabz.bookstore.order.dto.OrderDTO;
import com.bridgelabz.bookstore.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Table(name = "OrderDetails")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;
    private LocalDate orderDate;
    private int orderPrice;

    private String orderAddress;
    @JoinColumn(name = "user_Id")
    @ManyToOne
    private UserEntity userEntity;

    private int orderQuantity;
    private boolean cancel = false;


//    public OrderEntity(UserEntity userEntity,int orderPrice, OrderDTO orderDto) {
//        this.userEntity = userEntity;
//
//        this.orderDate = LocalDate.now();
//        this.quantity = orderDto.getQuantity();
//        this.orderAddress = orderDto.getAddress();
//        this.orderPrice = orderPrice;
//    }
}
