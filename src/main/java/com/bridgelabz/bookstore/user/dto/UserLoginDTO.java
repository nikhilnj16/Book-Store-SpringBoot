package com.bridgelabz.bookstore.user.dto;




import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class UserLoginDTO {

        private String userEmailId;
        private String userPassword;

}


