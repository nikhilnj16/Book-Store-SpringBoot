package com.bridgelabz.bookstore.order.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDTO {
//    private int orderId;
//    private LocalDate orderDate;
    private int orderPrice;
    private int orderQuantity;

//    private String token;
    private Boolean orderCancel;
    private String address;
}
