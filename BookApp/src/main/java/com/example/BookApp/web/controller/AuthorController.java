package com.example.BookApp.web.controller;

import com.example.BookApp.model.Author;
import com.example.BookApp.service.AuthorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/authors")
    public String getHomePage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Author> authors = this.authorService.getAllAuthors();
        model.addAttribute("authors", authors);
        model.addAttribute("bodyContent", "authors");
        return "authors";
    }

    @GetMapping("/authors3books")
    public String getAuthors(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Author> authors = this.authorService.getAuthorsWith3Books();
        model.addAttribute("authors", authors);
        model.addAttribute("bodyContent", "authors");
        return "authors3books";
    }


}