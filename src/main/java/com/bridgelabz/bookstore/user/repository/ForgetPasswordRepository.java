package com.bridgelabz.bookstore.user.repository;

import com.bridgelabz.bookstore.user.entity.ForgetPassword;
import com.bridgelabz.bookstore.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ForgetPasswordRepository extends JpaRepository<ForgetPassword, Integer> {
    @Query("select u from ForgetPassword u where u.otp = :otp and u.userEntity = :userEntity")
    ForgetPassword findByOtpAndUser(String otp, UserEntity userEntity);
    @Query("delete from ForgetPassword u where u.userEntity = :userEntity")
    void deleteByUserEntity(UserEntity userEntity);


}
