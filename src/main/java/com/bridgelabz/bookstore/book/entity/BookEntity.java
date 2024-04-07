package com.bridgelabz.bookstore.book.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    private String bookImageUrl;
    @NotNull
    private String bookName;
    @NotNull
    private String bookAuthor;
    @NotNull
    private String bookDescription;
    @NotNull
    private int bookPrice;
    @NotNull
    private int quantity;
}
