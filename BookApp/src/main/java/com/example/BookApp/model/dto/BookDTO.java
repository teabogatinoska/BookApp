package com.example.BookApp.model.dto;


import com.example.BookApp.model.Author;
import lombok.Data;

import javax.persistence.*;


@Data
public class BookDTO {


    private String bookTitle;

    private long ISBN;

    private int year;

    @ManyToOne
    private Author author;


    public BookDTO(String bookTitle, long ISBN, int year, Author author) {
        this.bookTitle = bookTitle;
        this.ISBN = ISBN;
        this.year = year;
        this.author = author;
    }

}
