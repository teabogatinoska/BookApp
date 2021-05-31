package com.example.BookApp.repository;

import com.example.BookApp.model.EBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EBookRepository extends JpaRepository<EBook, Long> {
    //Optional<EBook> findByName(String name);

    //void deleteByName(String name);
}
