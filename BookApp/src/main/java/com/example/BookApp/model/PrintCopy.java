package com.example.BookApp.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class PrintCopy extends Book{

   // @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    //private Long id;

    private int pagesNum;

    private int bookWeight;

    public PrintCopy( int pagesNum, int bookWeight) {
        //this.id = id;
        this.pagesNum = pagesNum;
        this.bookWeight = bookWeight;
    }

    public PrintCopy(Long id, String bookTitle, long ISBN, int year, Author author, int pagesNum, int bookWeight) {
        super(id, bookTitle, ISBN, year, author);
        //this.id = id1;
        this.pagesNum = pagesNum;
        this.bookWeight = bookWeight;
    }

    public PrintCopy() {

    }
}
