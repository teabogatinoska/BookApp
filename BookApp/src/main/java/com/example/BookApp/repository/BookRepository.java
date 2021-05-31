package com.example.BookApp.repository;

import com.example.BookApp.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    void deleteByBookTitle(String bookTitle);

    //Optional<Book> findByName(String name);
}
