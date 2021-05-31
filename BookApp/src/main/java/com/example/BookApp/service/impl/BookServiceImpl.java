package com.example.BookApp.service.impl;

import com.example.BookApp.model.Author;
import com.example.BookApp.model.Book;
import com.example.BookApp.model.dto.BookDTO;
import com.example.BookApp.model.EBook;
import com.example.BookApp.model.PrintCopy;
import com.example.BookApp.model.event.BookCreatedEvent;
import com.example.BookApp.repository.AuthorRepository;
import com.example.BookApp.repository.BookRepository;
import com.example.BookApp.repository.EBookRepository;
import com.example.BookApp.repository.PrintCopyRepository;
import com.example.BookApp.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.example.BookApp.model.exceptions.BookNotFoundException;
import com.example.BookApp.model.exceptions.AuthorNotFoundException;
import org.springframework.context.ApplicationEventPublisher;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;
    private AuthorRepository authorRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, ApplicationEventPublisher applicationEventPublisher) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public List<Book> getAllBooks() {
        return this.bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return this.bookRepository.findById(id);
    }

    @Override
    public void saveBook(Book book) {
        this.bookRepository.save(book);
    }

    @Override
    public Book getBookById(Long id) {
        Optional<Book> optional = bookRepository.findById(id);
        Book book = null;

        if (optional.isPresent()) {
            book = optional.get();
        } else {
            throw new RuntimeException("No book found with id :: " + id);
        }
        return book;
    }


    @Override
    public void deleteBookById(Long id) {
        this.bookRepository.deleteById(id);
    }

    @Override
    public List<Book> sortByYear() {
        List<Book> books = this.bookRepository.findAll();

        return books.stream()
                .sorted(Comparator.comparing(Book::getYear))
                .collect(Collectors.toList());
    }

    @Override
    public Book findOldestBook() {
        List<Book> books = this.bookRepository.findAll();

        return books.stream()
                .sorted(Comparator.comparing(Book::getYear))
                .findFirst().orElseThrow();
    }

    @Override
    public Book findNewestBook() {
        List<Book> books = this.bookRepository.findAll();

        return books.stream()
                .sorted(Comparator.comparing(Book::getYear).reversed())
                .findFirst().orElseThrow();
    }

    @Override
    public Optional<Book> save(BookDTO bookDto) {
        Author author = this.authorRepository.findById(bookDto.getAuthor().getId())
                .orElseThrow(() -> new AuthorNotFoundException(bookDto.getAuthor().getId()));
        this.bookRepository.deleteByBookTitle(bookDto.getBookTitle());
        Book book = new Book(bookDto.getBookTitle(), bookDto.getISBN(), bookDto.getYear(), author);
        this.bookRepository.save(book);

        this.applicationEventPublisher.publishEvent(new BookCreatedEvent(book));
        return Optional.of(book);
    }

    @Override
    public Optional<Book> save(String bookTitle, long ISBN, int year, Long authorId) {
        Author author = this.authorRepository.findById(authorId)
                .orElseThrow(() -> new AuthorNotFoundException(authorId));
        this.bookRepository.deleteByBookTitle(bookTitle);
        Book book = new Book(bookTitle, ISBN, year, author);
        this.bookRepository.save(book);

        this.applicationEventPublisher.publishEvent(new BookCreatedEvent(book));
        return Optional.of(book);
    }

    @Override
    public Optional<Book> edit(Long id, String bookTitle, long ISBN, int year, Long authorId) {
        Book book = this.bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        Author author = this.authorRepository.findById(authorId)
                .orElseThrow(() -> new AuthorNotFoundException(authorId));

        book.setBookTitle(bookTitle);
        book.setISBN(ISBN);
        book.setAuthor(author);
        book.setYear(year);

        return Optional.of(book);
    }

    @Override
    public Optional<Book> edit(Long id, BookDTO bookDto) {
        Book book = this.bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));

        book.setBookTitle(bookDto.getBookTitle());
        book.setISBN(bookDto.getISBN());
        book.setAuthor(bookDto.getAuthor());
        book.setYear(bookDto.getYear());

        Author author = this.authorRepository.findById(bookDto.getAuthor().getId())
                .orElseThrow(() -> new AuthorNotFoundException(bookDto.getAuthor().getId()));
        book.setAuthor(author);

        this.bookRepository.save(book);
        return Optional.of(book);
    }

    @Override
    public Page<Book> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {

        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.bookRepository.findAll(pageable);
    }


}
