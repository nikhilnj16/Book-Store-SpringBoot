package com.bridgelabz.bookstore.user.entity;


import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;



@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Table(name = "userDetails")
public class UserEntity {
    @Valid
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;
    @NotNull(message = "First name should not be null")
    @NotBlank(message = "name should not be blank")
    @Size(min = 2, max = 150, message = "First name must be between 2 and 150 characters")
    private String userFirstName;
    @NotNull(message = "Last name should not be null")
    @NotBlank(message = "name should not be blank")
    @Size(min = 2, max = 150, message = "Last name must be between 2 and 150 characters")
    private String userLastName;
    @Email(message = "Email is mandatory")
    private String userEmailId;
    @NotNull(message = "Last name should not be null")
    @NotBlank(message = "name should not be blank")
    @Size(min = 2, max = 200, message = "Password must be between 2 and 20 characters")
    private String userPassword;
    private int userAge;
    private String userGender;
    private Boolean userVerified;
    @OneToOne(mappedBy = "userEntity")
    private ForgetPassword forgetPassword;

}
