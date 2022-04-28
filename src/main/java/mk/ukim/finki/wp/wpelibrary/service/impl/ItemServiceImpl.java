package mk.ukim.finki.wp.wpelibrary.service.impl;

import mk.ukim.finki.wp.wpelibrary.model.Author;
import mk.ukim.finki.wp.wpelibrary.model.Item;
import mk.ukim.finki.wp.wpelibrary.model.Publisher;
import mk.ukim.finki.wp.wpelibrary.model.enumerations.Category;
import mk.ukim.finki.wp.wpelibrary.model.exception.InvalidItemIdException;
import mk.ukim.finki.wp.wpelibrary.model.exception.PublisherIdNotFoundException;
import mk.ukim.finki.wp.wpelibrary.repository.AuthorRepository;
import mk.ukim.finki.wp.wpelibrary.repository.ItemRepository;
import mk.ukim.finki.wp.wpelibrary.repository.PublisherRepository;
import mk.ukim.finki.wp.wpelibrary.service.AuthorService;
import mk.ukim.finki.wp.wpelibrary.service.ItemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final PublisherRepository publisherRepository;
    private final AuthorRepository authorRepository;

    public ItemServiceImpl(ItemRepository itemRepository, PublisherRepository publisherRepository,AuthorRepository authorRepository ) {
        this.itemRepository = itemRepository;
        this.publisherRepository = publisherRepository;
        this.authorRepository = authorRepository;
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
    public Optional<Item> save(Double price, String title,String description ,Integer quantity, String imglink, Category category, Long publisherId ,List<Long> authorId) {
        Publisher publisher=this.publisherRepository.findById(publisherId).orElseThrow(()-> new PublisherIdNotFoundException(publisherId));
        List<Author> authors=this.authorRepository.findAllById(authorId);
        this.itemRepository.deleteByTitle(title);
        return Optional.of(this.itemRepository.save(new Item(price,title,description,quantity,imglink,category,publisher)));
    }

    @Override
    @Transactional
    public Optional<Item> update(Long id, Double price, String title,String description , Integer quantity, String imglink, Category category, Long publisherId,List<Long> authorId) {
        Item item=this.findById(id).orElseThrow(InvalidItemIdException::new);
        item.setPrice(price);
        item.setTitle(title);
        item.setDescription(description);
        item.setQuantity(quantity);
        item.setImglink(imglink);
        item.setCategory(category);
        Publisher publisher=this.publisherRepository.findById(publisherId).orElseThrow(()-> new PublisherIdNotFoundException(publisherId));
        item.setPublisher(publisher);
        List<Author> authors=this.authorRepository.findAllById(authorId);
        item.setAuthor(authors);
        return Optional.of(this.itemRepository.save(item));
    }

    @Override
    public List<Item> filter(String title) {
        if(title !=null || !title.isEmpty()){
           return this.itemRepository.findAllByTitle(title);
        }
        else
            return itemRepository.findAll();
    }
}
