package com.bridgelabz.bookstore.user.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class ForgotPasswordDTO {
    private String userEmailId;
}
