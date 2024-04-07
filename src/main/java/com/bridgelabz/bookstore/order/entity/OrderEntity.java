package com.bridgelabz.bookstore.order.entity;

import com.bridgelabz.bookstore.book.entity.BookEntity;
import com.bridgelabz.bookstore.order.dto.OrderDTO;
import com.bridgelabz.bookstore.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

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
    @OneToOne
    private UserEntity userEntity;
    @JoinColumn(name = "book_Id")
    @ManyToOne
    private BookEntity bookEntity;
    private int quantity;
    private boolean cancel = false;


    public OrderEntity(UserEntity userEntity, BookEntity bookEntity,int orderPrice, OrderDTO orderDto) {
        this.userEntity = userEntity;
        this.bookEntity = bookEntity;
        this.orderDate = LocalDate.now();
        this.quantity = orderDto.getQuantity();
        this.orderAddress = orderDto.getAddress();
        this.orderPrice = orderPrice;
    }
}
