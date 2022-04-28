package mk.ukim.finki.wp.wpelibrary.service;

import mk.ukim.finki.wp.wpelibrary.model.Author;
import mk.ukim.finki.wp.wpelibrary.model.Item;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    Optional<Author> findById(Long id);
    List<Author> listAll();
    Author create(String name, String surname, String country);
    Author update(Long id,String name,String surname,String country);
    void deleteById(Long id);
}
