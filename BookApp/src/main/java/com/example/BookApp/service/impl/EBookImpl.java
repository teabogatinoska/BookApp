package com.example.BookApp.service.impl;

import com.example.BookApp.model.EBook;
import com.example.BookApp.repository.EBookRepository;
import com.example.BookApp.service.EBookService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EBookImpl implements EBookService {

    private EBookRepository eBookRepository;

    public EBookImpl(EBookRepository eBookRepository) {
        this.eBookRepository = eBookRepository;
    }

    @Override
    public List<EBook> getAllBooks() {
        return this.eBookRepository.findAll();
    }
}
