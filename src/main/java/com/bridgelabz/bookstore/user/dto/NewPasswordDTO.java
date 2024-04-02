package com.bridgelabz.bookstore.user.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class NewPasswordDTO {
    private String userEmailId;
    private String userPassword;
    private String otp;

}
