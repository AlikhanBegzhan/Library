package com.kooku.library.service;

import com.kooku.library.entity.Author;
import com.kooku.library.entity.Book;
import com.kooku.library.exception.ResourceNotFoundException;
import com.kooku.library.repository.AuthorRepository;
import com.kooku.library.repository.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public String createBook(String title, String isbn, String description, String publicationYear, Integer authorId) {
        Author author = authorRepository.findById(authorId).orElseThrow(
                () -> new ResourceNotFoundException("Author not found: Author ID - " + authorId)
        );
//        if (title == null || authorId == null) {
//            System.out.println("Book cannot be created: title or author ID is absent");
//        } else {
//            if (bookRepository.findByIsbn(isbn).isPresent()) {
//                System.out.println("Duplicate isbn: book already exists in database");
//            }
//            Book book = new Book(title, authorId, isbn, description, publicationYear);
//            bookRepository.save(book);
//        }
        Book book = new Book(title, isbn, description, publicationYear);
        book.setAuthor(author);
        bookRepository.save(book);
        return "Book successfully added";
    }

    public Book readBook(String isbn) {
        return bookRepository.findByIsbn(isbn).orElseThrow(
                () -> new ResourceNotFoundException("Book not found: ISBN - " + isbn)
        );
    }

    public String updateBook(String isbn, String newTitle, String newIsbn,
                             String newDescription, String newPublicationYear, Integer newAuthorId) {
        Book book = bookRepository.findByIsbn(isbn).orElseThrow(
                () -> new ResourceNotFoundException("Book not found: ISBN - " + isbn)
        );
        Author author = authorRepository.findById(newAuthorId).orElseThrow(
                () -> new ResourceNotFoundException("Author not found: Author ID - " + newAuthorId)
        );
        book.setTitle(newTitle);
        book.setIsbn(newIsbn);
        book.setDescription(newDescription);
        book.setPublicationYear(newPublicationYear);
        book.setAuthor(author);
        bookRepository.save(book);
        return "Book successfully updated";
    }

    @Transactional
    public String deleteBook(String isbn) {
        if (bookRepository.deleteByIsbn(isbn) > 0) {
            return "Book successfully deleted";
        } else {
            throw new ResourceNotFoundException("Book not found: ISBN - " + isbn);
        }
    }
}
