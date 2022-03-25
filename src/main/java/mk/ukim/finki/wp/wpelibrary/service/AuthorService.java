package mk.ukim.finki.wp.wpelibrary.service;

import mk.ukim.finki.wp.wpelibrary.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    Optional<Author> findById(Long id);
    List<Author> listAll();
    Author create(String name, String surname, String country);
    Author update(String name,String surname,String country);
    void deleteById(Long id);
}
