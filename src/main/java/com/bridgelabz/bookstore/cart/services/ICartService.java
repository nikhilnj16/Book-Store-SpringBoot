package com.bridgelabz.bookstore.cart.services;

import com.bridgelabz.bookstore.cart.dto.CartDTO;
import com.bridgelabz.bookstore.cart.entity.CartEntity;

import java.util.List;

public interface ICartService {
    CartEntity addToCart(String jwt, CartDTO cartDTO);

    String removeCart(int id);
    CartEntity changeCartQuantity(int userId, int cartId, CartDTO cartDTO);

    CartEntity getAllCartItems(int id);


    CartEntity updateCart(int userId, int cartId, int quantity);

    List<CartEntity> getAll();

    List<CartEntity>getCartDataByCartId(String token);



}
