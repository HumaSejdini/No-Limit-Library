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
import org.springframework.transaction.annotation.Transactional;


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
    public Optional<Item> findById(Long id) {
        return this.itemRepository.findById(id);
        //.orElseThrow(InvalidItemIdException::new) without Optional<>
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
    @Transactional
    public Optional<Item> save(Double price, String title,String description ,Integer quantity, String imglink, Category category, Long publisherId) {
        Publisher publisher=this.publisherRepository.findById(publisherId).orElseThrow(()-> new PublisherIdNotFoundException(publisherId));
        this.itemRepository.deleteByTitle(title);
        return Optional.of(this.itemRepository.save(new Item(price,title,description,quantity,imglink,category,publisher)));
    }

    @Override
    @Transactional
    public Optional<Item> update(Long id, Double price, String title,String description , Integer quantity, String imglink, Category category, Publisher publisher) {
        Item item=this.findById(id).orElseThrow(InvalidItemIdException::new);
        item.setPrice(price);
        item.setTitle(title);
        item.setDescription(description);
        item.setQuantity(quantity);
        item.setImglink(imglink);
        item.setCategory(category);
        item.setPublisher(publisher);
        return Optional.of(this.itemRepository.save(item));
    }
}
