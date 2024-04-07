package com.bridgelabz.bookstore.cart.repository;

import com.bridgelabz.bookstore.cart.entity.CartEntity;
import com.bridgelabz.bookstore.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ICartRepository extends JpaRepository<CartEntity, Integer> {
//    @Query("select c from CartEntity c where c.userEntity.userId =: userId")
//    List<CartEntity> getCartListByUserId(@Param("userId") int userId);

    @Query("SELECT c FROM CartEntity c WHERE c.userEntity.userId = :userId")
    List<CartEntity> findByUserId(@Param("userId") int userId);
//    @Query("SELECT u FROM UserEntity u WHERE u.userEmailId = :userEmailId")
//    UserEntity findByEmailId(@Param("userEmailId") String userEmailId);
}
