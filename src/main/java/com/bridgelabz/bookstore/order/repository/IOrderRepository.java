package com.bridgelabz.bookstore.order.repository;

import com.bridgelabz.bookstore.order.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderRepository extends JpaRepository<OrderEntity, Integer> {

}
