package com.bridgelabz.bookstore.book.services;

import com.bridgelabz.bookstore.book.entity.BookEntity;
import com.bridgelabz.bookstore.user.entity.UserEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

public interface IBookService {
    List<BookEntity> getBook();

    BookEntity postBook(BookEntity bookEntity);

    BookEntity putBook(@PathVariable int id, @RequestBody BookEntity bookEntity);
}
