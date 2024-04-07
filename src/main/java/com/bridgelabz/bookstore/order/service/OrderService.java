package com.bridgelabz.bookstore.order.service;

import com.bridgelabz.bookstore.book.entity.BookEntity;
import com.bridgelabz.bookstore.book.repository.IBookRepository;
import com.bridgelabz.bookstore.cart.repository.ICartRepository;
import com.bridgelabz.bookstore.order.dto.OrderDTO;
import com.bridgelabz.bookstore.order.entity.OrderEntity;
import com.bridgelabz.bookstore.order.repository.IOrderRepository;
import com.bridgelabz.bookstore.user.entity.UserEntity;
import com.bridgelabz.bookstore.user.repository.IUserRepository;
import com.bridgelabz.bookstore.user.utility.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.UserDataHandler;

import java.util.Date;
import java.util.List;

@Service
public class OrderService implements IOrderService {


    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IBookRepository bookRepository;
    @Autowired
    private ICartRepository cartRepository;
    @Autowired
    private IOrderRepository orderRepository;
    @Autowired
    private EmailSender emailSender;


    @Override
    public OrderEntity placeOrder(int userId, OrderDTO orderDTO) {
        UserEntity userEntity = userRepository.findById(userId).orElse(null);
        BookEntity bookEntity = bookRepository.findById(orderDTO.getBookId()).orElse(null);

        if(userEntity != null){
            int orderPrice = bookEntity.getBookPrice() * orderDTO.getQuantity();
            bookEntity.setQuantity(bookEntity.getQuantity()-orderDTO.getQuantity());

            OrderEntity orderEntity = new OrderEntity(userEntity,bookEntity,orderPrice,orderDTO);
            orderRepository.save(orderEntity);
            emailSender.sendEmail(userEntity.getUserEmailId(), "Order placed successfully",
                    "Book Name :" + orderEntity.getBookEntity().getBookName() +
                    "\n" + "Book Price :" + orderEntity.getBookEntity().getBookPrice() +
                    "\n" + "Order Quantity:" + orderDTO.getQuantity() + "Order Price:" + orderPrice);
            return orderEntity;
        };
        return null;
    }

    @Override
    public List<OrderEntity> getUserOrders(int userId) {
//        UserEntity userEntity = userRepository.findById(userId).orElse(null);
//
//        if(userEntity != null){
//            List<OrderEntity> orderEntityList = orderRepository.findAllById(userId);
//            return orderEntityList;
//        }
        return null;
    }

    @Override
    public String cancelOrder(int orderId, int userId) {
        if(orderRepository.existsById(orderId) && userRepository.existsById(userId)){
        UserEntity userEntity = userRepository.findById(userId).orElse(null);

        OrderEntity orderEntity = orderRepository.findById(orderId).orElse(null);

        orderEntity.setCancel(true);
        BookEntity bookEntity = bookRepository.findById(orderEntity.getOrderId()).orElse(null);
        bookEntity.setQuantity(bookEntity.getQuantity()+ orderEntity.getQuantity());
        emailSender.sendEmail(userEntity.getUserEmailId(), "Order Cancelled", "Order Id " +
                        orderId + '\n' + orderEntity);
                orderRepository.save(orderEntity);
                return "Order Cancelled";}

                else {
                return "Order not present";
            }

    }
}
