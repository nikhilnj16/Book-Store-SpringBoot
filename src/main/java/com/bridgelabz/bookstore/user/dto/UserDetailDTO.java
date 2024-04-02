package com.bridgelabz.bookstore.user.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString

public class UserDetailDTO {
    private String userFirstName;
    private String userLastName;
    private String userEmailId;
    private String userAge;
    private String userGender;
}
