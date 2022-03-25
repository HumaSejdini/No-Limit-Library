package mk.ukim.finki.wp.wpelibrary.service.impl;

import mk.ukim.finki.wp.wpelibrary.model.Item;
import mk.ukim.finki.wp.wpelibrary.model.Publisher;
import mk.ukim.finki.wp.wpelibrary.model.enumerations.Category;
import mk.ukim.finki.wp.wpelibrary.model.exception.InvalidItemIdException;
import mk.ukim.finki.wp.wpelibrary.model.exception.PublisherIdNotFoundException;
import mk.ukim.finki.wp.wpelibrary.repository.ItemRepository;
import mk.ukim.finki.wp.wpelibrary.repository.PublisherRepository;
import mk.ukim.finki.wp.wpelibrary.service.ItemService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final PublisherRepository publisherRepository;

    public ItemServiceImpl(ItemRepository itemRepository, PublisherRepository publisherRepository) {
        this.itemRepository = itemRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public Item findById(Long id) {
        return this.itemRepository.findById(id).orElseThrow(InvalidItemIdException::new);
    }

    @Override
    public Optional<Item> findByTitle(String title) {
        return this.itemRepository.findByTitle(title);
    }

    @Override
    public List<Item> findAll() {
        return this.itemRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        this.itemRepository.deleteById(id);
    }

    @Override
    public Optional<Item> save(Double price, String title, Integer quantity, String imglink, Category category, Long publisherId) {
        Publisher publisher=this.publisherRepository.findById(publisherId).orElseThrow(()-> new PublisherIdNotFoundException(publisherId));
        return Optional.of(this.itemRepository.save(new Item(price,title,quantity,imglink,category,publisher)));
    }

    @Override
    public Optional<Item> update(Long id, Double price, String title, Integer quantity, String imglink, Category category, Publisher publisher) {
        return Optional.empty();
    }
}
