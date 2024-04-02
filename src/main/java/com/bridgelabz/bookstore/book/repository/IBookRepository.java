package com.bridgelabz.bookstore.book.repository;

import com.bridgelabz.bookstore.book.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBookRepository extends JpaRepository<BookEntity, Integer> {
}
