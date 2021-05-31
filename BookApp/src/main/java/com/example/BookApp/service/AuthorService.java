package com.example.BookApp.service;

import com.example.BookApp.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {


    List<Author> getAllAuthors();

    List<Author> getAuthorsWith3Books();

    Author getAuthorById(long id);

    Optional<Author> findById(Long id);

    void saveAuthor (Author author);
}
