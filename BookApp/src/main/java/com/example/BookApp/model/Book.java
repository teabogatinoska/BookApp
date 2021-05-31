package com.example.BookApp.model;


import lombok.Data;
import org.hibernate.annotations.DiscriminatorOptions;

import javax.persistence.*;



@Entity
@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorOptions(force = true)
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bookTitle;

    private long ISBN;

    private int year;

    @ManyToOne
    private Author author;


    public Book(Long id, String bookTitle, long ISBN, int year, Author author) {
        this.id = id;
        this.bookTitle = bookTitle;
        this.ISBN = ISBN;
        this.year = year;
        this.author = author;
    }
    public Book(String bookTitle, long ISBN, int year, Author author) {

        this.bookTitle = bookTitle;
        this.ISBN = ISBN;
        this.year = year;
        this.author = author;
    }
    public Book(){}


}
