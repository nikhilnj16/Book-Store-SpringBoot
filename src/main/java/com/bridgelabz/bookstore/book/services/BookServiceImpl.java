package com.bridgelabz.bookstore.book.services;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.bridgelabz.bookstore.book.entity.BookEntity;
import com.bridgelabz.bookstore.book.repository.IBookRepository;
import com.bridgelabz.bookstore.user.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements IBookService {

    @Autowired
    private IBookRepository iBookRepository;

    public List<BookEntity> getBook(){
        return iBookRepository.findAll();
    }

    public BookEntity postBook(BookEntity bookEntity) {
        return iBookRepository.save(bookEntity);
    }

    public BookEntity putBook(@PathVariable int id, @RequestBody BookEntity bookEntity) {
        BookEntity oldBook = iBookRepository.findById(id).get();
//        if(bookEntity.getBookId())
        oldBook.setBookId(bookEntity.getBookId());
        oldBook.setBookName(bookEntity.getBookName());
        oldBook.setBookAuthor(bookEntity.getBookAuthor());
        oldBook.setBookDescription(bookEntity.getBookDescription());
        oldBook.setBookPrice(bookEntity.getBookPrice());
        oldBook.setQuantity(bookEntity.getQuantity());
        iBookRepository.save(oldBook);
        return oldBook;

    }
}
