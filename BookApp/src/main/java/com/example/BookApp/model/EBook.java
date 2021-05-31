package com.example.BookApp.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class EBook extends Book{

    //@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    //private Long id;

    private String format;

    private double MBNum;


    public EBook(Long id, String bookTitle, long ISBN, int year, Author author, String format, double MBNum) {
        super(id, bookTitle, ISBN, year, author);
       // this.id = id1;
        this.format = format;
        this.MBNum = MBNum;
    }

    public EBook( String format, double MBNum) {
        //this.id = id;
        this.format = format;
        this.MBNum = MBNum;
    }

    public EBook() {

    }
}
