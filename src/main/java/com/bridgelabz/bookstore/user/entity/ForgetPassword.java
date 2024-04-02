package com.bridgelabz.bookstore.user.entity;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.*;

import java.util.Date;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ForgetPassword {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer fpId;
    @Column(nullable = false)
    private Integer otp;
    @Column(nullable = false)
    private Date expirationTime;
    @OneToOne
    private UserEntity userEntity;
}
