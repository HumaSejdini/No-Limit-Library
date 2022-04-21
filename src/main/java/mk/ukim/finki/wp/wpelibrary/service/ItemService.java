package mk.ukim.finki.wp.wpelibrary.service;

import mk.ukim.finki.wp.wpelibrary.model.Item;
import mk.ukim.finki.wp.wpelibrary.model.Publisher;
import mk.ukim.finki.wp.wpelibrary.model.enumerations.Category;

import java.util.List;
import java.util.Optional;

public interface ItemService {
    Optional<Item> findById(Long id);
    Optional<Item> findByTitle(String title);
    List<Item> findAll();
    void deleteById(Long id);
    Optional<Item> save(Double price, String title,String description, Integer quantity, String imglink, Category category, Long publisherId);
    Optional<Item> update(Long id,Double price, String title,String description, Integer quantity, String imglink, Category category, Publisher publisher);
    List<Item> search(String title);
}
