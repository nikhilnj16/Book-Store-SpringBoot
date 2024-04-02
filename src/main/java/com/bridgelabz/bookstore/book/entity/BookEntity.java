package com.bridgelabz.bookstore.book.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString

public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookId;
    private String bookName;
    private String bookAuthor;
    private String bookDescription;
    private int bookPrice;
    private int quantity;
}
