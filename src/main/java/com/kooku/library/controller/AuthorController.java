package com.kooku.library.controller;

import com.kooku.library.entity.Author;
import com.kooku.library.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/author")
@RestController
public class AuthorController {

    @Autowired
    AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping("/createAuthor")
    public String createAuthor(@RequestParam(name = "authorName") String name,
                               @RequestParam(name = "authorBiography", required = false) String biography,
                               @RequestParam(name = "authorBirthYear") String birthYear) {
        return authorService.createAuthor(name, biography, birthYear);
    }

    @GetMapping("/readAuthor")
    public Author readAuthor(@RequestParam(name = "authorName") String name,
                             @RequestParam(name = "authorBirthYear") String birthYear) {
        return authorService.readAuthor(name, birthYear);
    }

    @PostMapping("/updateAuthor")
    public String updateAuthor(@RequestParam(name = "authorName") String name,
                               @RequestParam(name = "authorBirthYear") String birthYear,
                               @RequestParam(name = "newAuthorName") String newName,
                               @RequestParam(name = "newAuthorBiography", required = false) String newBiography,
                               @RequestParam(name = "newAuthorBirthYear") String newBirthYear) {
        return authorService.updateAuthor(name, birthYear, newName, newBiography, newBirthYear);
    }

    @PostMapping("/deleteAuthor")
    public String deleteAuthor(@RequestParam(name = "authorName") String name,
                               @RequestParam(name = "authorBirthYear") String birthYear) {
        return authorService.deleteAuthor(name, birthYear);
    }
}
