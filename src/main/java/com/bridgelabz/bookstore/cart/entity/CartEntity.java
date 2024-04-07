package com.bridgelabz.bookstore.cart.entity;

import com.bridgelabz.bookstore.book.entity.BookEntity;
import com.bridgelabz.bookstore.cart.dto.CartDTO;
import com.bridgelabz.bookstore.user.entity.UserEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.apache.catalina.User;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

@Table(name = "cartDetails")

public class CartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cartId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private BookEntity bookEntity;

    private int cartQuantity;
    private int cartTotalPrice;

    public CartEntity(UserEntity userEntity , BookEntity bookEntity, int cartTotalPrice, CartDTO cartDto) {
        this.userEntity = userEntity;
        this.bookEntity = bookEntity;
        this.cartQuantity = cartDto.getQuantity();
        this.cartTotalPrice = cartTotalPrice;
    }
}
