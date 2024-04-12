package com.bridgelabz.bookstore.cart.services;

import com.bridgelabz.bookstore.book.entity.BookEntity;
import com.bridgelabz.bookstore.book.repository.IBookRepository;
import com.bridgelabz.bookstore.book.services.BookServiceImpl;
import com.bridgelabz.bookstore.cart.dto.CartDTO;
import com.bridgelabz.bookstore.cart.entity.CartEntity;
import com.bridgelabz.bookstore.cart.repository.ICartRepository;
import com.bridgelabz.bookstore.cart.utility.CartValidator;
import com.bridgelabz.bookstore.user.entity.UserEntity;
import com.bridgelabz.bookstore.user.repository.IUserRepository;
import com.bridgelabz.bookstore.user.utility.UserJwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements ICartService{


    @Autowired
    UserJwt userJwt;

    private final ICartRepository cartRepository;
    private final IBookRepository bookRepository;

    private final BookServiceImpl bookService;
    private final IUserRepository userRepository;

    private final CartValidator cartValidator;

    @Autowired
    public CartServiceImpl(ICartRepository cartRepository, IBookRepository bookRepository, BookServiceImpl bookService, IUserRepository userRepository, CartValidator cartValidator) {
        this.cartRepository = cartRepository;
        this.bookRepository = bookRepository;
        this.bookService = bookService;
        this.userRepository = userRepository;
        this.cartValidator = cartValidator;
    }

    @Override
    public CartEntity addToCart(String jwt, CartDTO cartDTO) {
        try{
            cartValidator.validate(cartDTO);
        } catch (IllegalArgumentException ex){
            return null;
        }

        System.out.println(jwt);
        String userFirstName = (userJwt.decodeToken(jwt));
        System.out.println("userFirstName" + userFirstName);
        UserEntity userEntity = userRepository.findByFirstName(userFirstName);


        BookEntity bookEntity = bookRepository.findById(cartDTO.getBookId()).orElse(null);
        System.out.println(bookEntity);
        assert bookEntity != null;
        int cartPrice = bookEntity.getBookPrice()*cartDTO.getQuantity();
        int cartQty = cartDTO.getQuantity();
        CartEntity cartEntity = new CartEntity(userEntity,bookEntity,cartPrice,cartDTO);
        cartRepository.save(cartEntity);
        return cartEntity;
    }

    @Override
    public String removeCart(int id) {


        if (cartRepository.existsById(id)){
            cartRepository.deleteById(id);
            return "Cart Removed successfully";
        }
        return "Cart does not exist ";

//        try {
//              throw new Exception("Cart cannot be null0");
//            } catch (Exception ex){
//                throw new RuntimeException(ex);
//            }
    }

    @Override
    public CartEntity changeCartQuantity(int userId, int cartId, CartDTO cartDTO) {
        UserEntity userEntity = userRepository.findById(userId).orElse(null);
        BookEntity bookEntity = bookRepository.findById(cartDTO.getBookId()).orElse(null);
        CartEntity cartEntity = cartRepository.findById(cartId).orElse(null);

        if(cartEntity != null && userEntity != null){
            if(bookEntity !=null){
//                cartEntity.setBookEntity(bookEntity);
                cartEntity.setCartQuantity(cartDTO.getQuantity());
                cartEntity.setCartTotalPrice(bookEntity.getBookPrice() * cartDTO.getQuantity());
                return cartRepository.save(cartEntity);



            }
        }
        return null;
    }

    @Override
    public CartEntity getAllCartItems(int id) {
        return null;
    }

    @Override
    public CartEntity updateCart(int userId, int cartId, int quantity) {
        UserEntity userEntity = userRepository.findById(userId).orElse(null);
        CartEntity cartEntity = cartRepository.findById(cartId).orElse(null);

        cartEntity.setCartQuantity(quantity);
        cartEntity = cartRepository.save(cartEntity);
        System.out.println(quantity);
        System.out.println(cartEntity);
        return null;
    }

    @Override
    public List<CartEntity> getAll() {
        return cartRepository.findAll();
    }

    @Override
    public List<CartEntity> getCartDataByCartId(String token) {
        String userFirstName = (userJwt.decodeToken(token));
        UserEntity userEntity = userRepository.findByFirstName(userFirstName);
        return cartRepository.findByUserId((int) userEntity.getUserId());

    }


}
