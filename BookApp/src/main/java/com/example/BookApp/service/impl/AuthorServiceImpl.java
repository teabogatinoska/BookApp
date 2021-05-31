package com.example.BookApp.service.impl;

import com.example.BookApp.model.Author;
import com.example.BookApp.model.Book;
import com.example.BookApp.repository.AuthorRepository;
import com.example.BookApp.repository.BookRepository;
import com.example.BookApp.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }


    @Override
    public List<Author> getAllAuthors() {
        return this.authorRepository.findAll();
    }

    @Override
    public List<Author> getAuthorsWith3Books() {
        List<Author> authors = this.authorRepository.findAll();
        List<Book> books = this.bookRepository.findAll();
        List<Author> moreThan3Books = new ArrayList<>();
        int counter = 0;

        for(Author author : authors) {
            for (Book b : books) {
                if (b.getAuthor().equals(author)){
                    counter++;
                }
            }
            if(counter > 3){
                moreThan3Books.add(author);
                counter = 0;
            }
        }
        return moreThan3Books;
    }


    @Override
    public Author getAuthorById(long id) {
        Optional<Author> optional = this.authorRepository.findById(id);
        Author author = null;

        if (optional.isPresent()) {
            author = optional.get();
        } else {
            throw new RuntimeException("No author found with id :: " + id);
        }
        return author;
    }

    @Override
    public Optional<Author> findById(Long id) {
        return this.authorRepository.findById(id);
    }

    @Override
    public void saveAuthor(Author author) {
        this.authorRepository.save(author);
    }


}
