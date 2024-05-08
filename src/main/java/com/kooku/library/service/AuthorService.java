package com.kooku.library.service;

import com.kooku.library.entity.Author;
import com.kooku.library.exception.DuplicateResourceException;
import com.kooku.library.exception.EmptyFieldException;
import com.kooku.library.exception.ResourceNotFoundException;
import com.kooku.library.repository.AuthorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public String createAuthor(String name, String biography, String birthYear) {
        if (name.isBlank() || birthYear.isBlank()) {
            throw new EmptyFieldException("Author cannot be created: name or birth year is empty");
        } else {
            if (authorRepository.findByNameAndBirthYear(name, birthYear).isPresent()) {
                throw new DuplicateResourceException("Duplicate author: name - " + name + "; birth year - " + birthYear);
            }
            Author author = new Author(name, biography, birthYear);
            authorRepository.save(author);
        }
        return "Author successfully added";
    }

    public Author readAuthor(String name, String birthYear) {
        return authorRepository.findByNameAndBirthYear(name, birthYear).orElseThrow(
                () -> new ResourceNotFoundException(
                        "Author not found: name - " + name + "; birth year - " + birthYear)
        );
    }

    public String updateAuthor(String name, String birthYear,
                               String newName, String newBiography, String newBirthYear) {
        Author author = authorRepository.findByNameAndBirthYear(name, birthYear).orElseThrow(
                () -> new ResourceNotFoundException(
                        "Author not found: name - " + name + "; birth year - " + birthYear)
        );
        author.setName(newName);
        author.setBiography(newBiography);
        author.setBirthYear(newBirthYear);
        authorRepository.save(author);
        return "Author successfully updated";
    }

    @Transactional
    public String deleteAuthor(String name, String birthYear) {
        if (authorRepository.deleteByNameAndBirthYear(name, birthYear) > 0) {
            return "Author successfully deleted";
        } else {
            throw new ResourceNotFoundException("Author not found: name - " + name + "; birth year - " + birthYear);
        }
    }
}
