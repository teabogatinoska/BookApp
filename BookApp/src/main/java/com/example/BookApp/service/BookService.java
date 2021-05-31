package com.example.BookApp.service;

import com.example.BookApp.model.Book;
import com.example.BookApp.model.dto.BookDTO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> getAllBooks();

    Optional<Book> findById(Long id);

    void saveBook(Book book);

    Book getBookById(Long id);

    void deleteBookById(Long id);

    List<Book> sortByYear();

    Book findOldestBook();

    Book findNewestBook();

    Optional<Book> save(BookDTO bookDto);

    Optional<Book> save(String bookTitle, long ISBN, int year, Long authorId);

    Optional<Book> edit(Long id, String bookTitle, long ISBN, int year, Long authorId);

    Optional<Book> edit(Long id, BookDTO bookDto);

    Page<Book> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
