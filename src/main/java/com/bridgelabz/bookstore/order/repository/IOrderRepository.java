package com.bridgelabz.bookstore.order.repository;

import com.bridgelabz.bookstore.order.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrderRepository extends JpaRepository<OrderEntity, Integer> {
//    List<OrderEntity> findAllByUserEntity_id(long userId);
}
