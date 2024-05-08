package com.kooku.library.repository;

import com.kooku.library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Integer> {

    Optional<Book> findByTitle(String title);
    Optional<Book> findByIsbn(String isbn);

    Integer deleteByIsbn(String isbn);
}
