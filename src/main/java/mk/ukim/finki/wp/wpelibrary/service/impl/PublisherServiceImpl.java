package mk.ukim.finki.wp.wpelibrary.service.impl;

import mk.ukim.finki.wp.wpelibrary.model.Publisher;
import mk.ukim.finki.wp.wpelibrary.model.exception.InvalidPublisherIdException;
import mk.ukim.finki.wp.wpelibrary.model.exception.PublisherIdNotFoundException;
import mk.ukim.finki.wp.wpelibrary.repository.PublisherRepository;
import mk.ukim.finki.wp.wpelibrary.service.PublisherService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherServiceImpl implements PublisherService {
    private final PublisherRepository publisherRepository;

    public PublisherServiceImpl(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    @Override
    public Publisher findById(Long id) {
        return this.publisherRepository.findById(id).orElseThrow(InvalidPublisherIdException::new);
    }

    @Override
    public List<Publisher> findAll() {
        return this.publisherRepository.findAll();
    }

    @Override
    public Publisher create(String name, String contact, String address) {
        if(name==null || contact==null || address==null){
            throw new IllegalArgumentException();
        }
        Publisher publisher=new Publisher(name,contact,address);
        return this.publisherRepository.save(publisher);
    }

    @Override
    public Publisher update(Long id, String name, String contact, String address) {
        Publisher publisher=this.findById(id);
        publisher.setName(name);
        publisher.setContact(contact);
        publisher.setAddress(address);
        return this.publisherRepository.save(publisher);
    }

    @Override
    public void delete(Long id) {
        this.publisherRepository.deleteById(id);
    }

    @Override
    public Publisher findByName(String name) {
        return this.publisherRepository.findByName(name);
    }
}
