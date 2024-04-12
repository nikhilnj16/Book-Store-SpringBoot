package com.bridgelabz.bookstore.cart.controller;

import com.bridgelabz.bookstore.cart.dto.CartDTO;
import com.bridgelabz.bookstore.cart.entity.CartEntity;
import com.bridgelabz.bookstore.cart.services.ICartService;
import com.bridgelabz.bookstore.user.dto.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private ICartService cartService;





    @PostMapping("/add/{jwt}")
    public ResponseEntity<ResponseDTO> addToCart(@PathVariable String jwt, @RequestBody CartDTO cartDTO){
        ResponseDTO responseDTO = new ResponseDTO("Added to cart", cartService.addToCart(jwt,cartDTO));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/remove/{cartId}")
    public ResponseEntity<ResponseDTO> removeCartById(@PathVariable int cartId){
        String message = cartService.removeCart(cartId);
        ResponseDTO responseDTO = new ResponseDTO("Status ", message);
        return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
    }

    @PostMapping("/updateQuantity/{userId}/{cartId}")
    public ResponseEntity<ResponseDTO> changeBookQuantity(@PathVariable int userId, @PathVariable int cartId, @RequestBody CartDTO cartDTO){
        CartEntity cartEntity = cartService.changeCartQuantity(userId,cartId,cartDTO);
        ResponseDTO responseDTO = new ResponseDTO("cart qty updated!", cartEntity);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    @GetMapping("/getAllCartItems")
    public ResponseEntity<ResponseDTO> getAllCartItems(){
        List<CartEntity> cartEntityList = cartService.getAll();
        ResponseDTO responseDTO = new ResponseDTO("CartList", cartEntityList);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/CartByUser/{token}")
    public List<CartEntity> getCartDataByUserId(@PathVariable String token){

        return cartService.getCartDataByCartId(token);

    }

    @PutMapping("/updateQty/{userId}/{cartId}/{quantity}")
    public ResponseEntity<ResponseDTO> updateQuantity(@PathVariable int userId, @PathVariable int cartId, @PathVariable int quantity){
        CartEntity cartEntity = cartService.updateCart(userId,cartId,quantity);
        ResponseDTO responseDTO = new ResponseDTO("cart qty changed!", cartEntity);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }


}
