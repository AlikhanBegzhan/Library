package com.kooku.library.controller;

import com.kooku.library.entity.Book;
import com.kooku.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/book")
@RestController
public class BookController {

    @Autowired
    BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/createBook")
    public String createBook(@RequestParam(name = "bookTitle") String bookTitle,
                             @RequestParam(name = "bookIsbn") String bookIsbn,
                             @RequestParam(name = "bookDescription", required = false) String bookDescription,
                             @RequestParam(name = "bookPublicationYear") String bookPublicationYear,
                             @RequestParam(name = "bookAuthorId") Integer bookAuthorId) {
        return bookService.createBook(bookTitle, bookIsbn, bookDescription, bookPublicationYear, bookAuthorId);
    }

    @GetMapping("/readBook")
    public Book readBook(@RequestParam(name = "bookIsbn") String bookIsbn) {
        return bookService.readBook(bookIsbn);
    }

    @PostMapping("/updateBook")
    public String updateBook(@RequestParam(name = "bookIsbn") String bookIsbn,
                             @RequestParam(name = "newBookTitle") String newBookTitle,
                             @RequestParam(name = "newBookIsbn") String newBookIsbn,
                             @RequestParam(name = "newBookDescription", required = false) String newBookDescription,
                             @RequestParam(name = "newBookPublicationYear") String newBookPublicationYear,
                             @RequestParam(name = "newBookAuthorId") Integer newBookAuthorId) {
        return bookService.updateBook(
                bookIsbn, newBookTitle, newBookIsbn, newBookDescription, newBookPublicationYear, newBookAuthorId);
    }

    @PostMapping("/deleteBook")
    public String deleteBook(@RequestParam(name = "bookIsbn") String bookIsbn) {
        return bookService.deleteBook(bookIsbn);
    }
}
