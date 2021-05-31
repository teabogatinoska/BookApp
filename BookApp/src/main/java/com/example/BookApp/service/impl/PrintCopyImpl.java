package com.example.BookApp.service.impl;

import com.example.BookApp.model.PrintCopy;
import com.example.BookApp.repository.PrintCopyRepository;
import com.example.BookApp.service.PrintCopyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrintCopyImpl implements PrintCopyService {

    private PrintCopyRepository printCopyRepository;

    public PrintCopyImpl(PrintCopyRepository printCopyRepository) {
        this.printCopyRepository = printCopyRepository;
    }

    @Override
    public List<PrintCopy> getAllBooks() {
        return this.printCopyRepository.findAll();
    }
}