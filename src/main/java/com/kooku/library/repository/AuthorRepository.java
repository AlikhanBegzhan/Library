package com.kooku.library.repository;

import com.kooku.library.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Integer> {

    Optional<Author> findByName(String name);

    Optional<Author> findByNameAndBirthYear(String name, String birthYear);

    Optional<Author> findByNameAndBiographyAndBirthYear(String name, String biography, String birthYear);

    Integer deleteByNameAndBirthYear(String name, String birthYear);
}
