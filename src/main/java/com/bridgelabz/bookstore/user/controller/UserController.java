package com.bridgelabz.bookstore.user.controller;


import com.bridgelabz.bookstore.user.dto.NewPasswordDTO;
import com.bridgelabz.bookstore.user.dto.ResetPasswordDTO;
import com.bridgelabz.bookstore.user.utility.EmailSender;
import com.bridgelabz.bookstore.user.utility.UserJwt;
import com.bridgelabz.bookstore.user.dto.UserLoginDTO;
import com.bridgelabz.bookstore.user.entity.UserEntity;
import com.bridgelabz.bookstore.user.services.IUserReg;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserReg service;

    @Autowired
    UserJwt userJwt;

    @Autowired
    EmailSender emailSender;
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> userRegistration(@Valid @RequestBody UserEntity userEntity){
        service.userRegistration(userEntity);
        return new ResponseEntity<>("User registered successfully!", HttpStatus.OK);
    }

    @PostMapping("/login")
    public String userLogin(@Valid @RequestBody UserLoginDTO userLoginDto){
        return service.userLogin(userLoginDto);
    }

    @GetMapping("/getUser")
    public UserEntity getUser(@RequestHeader String token){
        return service.getUser(token);
    }

    @PostMapping("/forgetPassword")
    public String forgetPassword(@RequestHeader String email){
        return service.forgetPassword(email);
    }

    @PostMapping("/newPassword")
    public String newPassword(@RequestBody NewPasswordDTO newPasswordDTO){
        return service.newPassword(newPasswordDTO);
    }

    @PostMapping("/resetPassword")
    public String resetPassword(@RequestBody ResetPasswordDTO resetPasswordDTO){
        return service.resetPassword(resetPasswordDTO);
    }


}