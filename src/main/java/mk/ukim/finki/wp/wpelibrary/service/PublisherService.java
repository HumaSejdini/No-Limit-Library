package mk.ukim.finki.wp.wpelibrary.service;

import mk.ukim.finki.wp.wpelibrary.model.Publisher;

import java.util.List;

public interface PublisherService {
    Publisher findById(Long id);
    List<Publisher> findAll();
    Publisher create(String name, String contact, String address);
    Publisher update(Long id,String name, String contact, String address);
    void delete(Long id);
    Publisher findByName(String name);
}
