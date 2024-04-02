package com.bridgelabz.bookstore.user.dto;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class ResetPasswordDTO {
    private String userEmailId;
    private String userOldPassword;
    private String userNewPassword;
}
