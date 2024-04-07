package com.bridgelabz.bookstore.book.controller;

import com.bridgelabz.bookstore.book.entity.BookEntity;
import com.bridgelabz.bookstore.book.services.BookServiceImpl;
import com.bridgelabz.bookstore.book.services.IBookService;
import com.bridgelabz.bookstore.user.entity.UserEntity;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class BookController {

    @Autowired
    private IBookService iBookService;

    @GetMapping("/getBook")
    public List<BookEntity> getBook(){
        return iBookService.getBook();
    }
    @PostMapping("/postBook")
    public BookEntity postBook(@RequestBody BookEntity bookEntity){
        return iBookService.postBook(bookEntity);
    }
    @PutMapping("/putBook/{id}")
    public BookEntity putBook(@PathVariable int id, @RequestBody BookEntity bookEntity){
        return iBookService.putBook(id , bookEntity);
    }

}
