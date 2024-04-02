package com.bridgelabz.bookstore.user.services;


import com.bridgelabz.bookstore.user.dto.ResetPasswordDTO;
import com.bridgelabz.bookstore.user.dto.UserLoginDTO;
import com.bridgelabz.bookstore.user.dto.NewPasswordDTO;
import com.bridgelabz.bookstore.user.entity.UserEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface IUserReg {

    void userRegistration(UserEntity userEntity);
    String userLogin(UserLoginDTO userLoginDto);

    UserEntity getUser(String token);
    String forgetPassword(@RequestBody String email);
    String newPassword(@RequestBody NewPasswordDTO newPasswordDTO);

    String resetPassword(@RequestBody ResetPasswordDTO resetPasswordDTO);

//    List<UserLoginDto> getAllUserDetails();
//    List<UserLoginDto> getUserById(@PathVariable int id);
//
//    void postUser(@RequestBody UserEntity userEntity);
//    UserEntity putUserDetails(@PathVariable int id);
//    void deleteUser(@PathVariable int id);
}
