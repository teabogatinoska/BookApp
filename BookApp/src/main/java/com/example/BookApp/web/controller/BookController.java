package com.example.BookApp.web.controller;

import com.example.BookApp.service.AuthorService;
import com.example.BookApp.service.BookService;
import org.springframework.stereotype.Controller;
import com.example.BookApp.model.Book;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BookController {
    private final BookService bookService;
    private final AuthorService authorService;


    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }


    @GetMapping
    public String getHomePage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Book> books = this.bookService.sortByYear();

        model.addAttribute("books", books);
        model.addAttribute("bodyContent", "books");
        return "index";
    }

    @GetMapping("/oldestBook")
    public String getOldestBook(@RequestParam(required = false) String error, Model model){
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        Book book = this.bookService.findOldestBook();

        model.addAttribute("book", book);
        model.addAttribute("bodyContent", "book");
        return "oldestBook";
    }
    @GetMapping("/newestBook")
    public String getNewestBook(@RequestParam(required = false) String error, Model model){
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        Book book = this.bookService.findNewestBook();

        model.addAttribute("book", book);
        model.addAttribute("bodyContent", "book");
        return "newestBook";
    }


}