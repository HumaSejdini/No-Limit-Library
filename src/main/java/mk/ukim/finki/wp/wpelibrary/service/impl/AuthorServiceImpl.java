package mk.ukim.finki.wp.wpelibrary.service.impl;

import mk.ukim.finki.wp.wpelibrary.model.Author;
import mk.ukim.finki.wp.wpelibrary.model.Item;
import mk.ukim.finki.wp.wpelibrary.model.exception.InvalidAuthorIdException;
import mk.ukim.finki.wp.wpelibrary.model.exception.InvalidItemIdException;
import mk.ukim.finki.wp.wpelibrary.repository.AuthorRepository;
import mk.ukim.finki.wp.wpelibrary.service.AuthorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Optional<Author> findById(Long id) {
        return this.authorRepository.findById(id);
    }

    @Override
    public List<Author> listAll() {
        return this.authorRepository.findAll();
    }

    @Override
    @Transactional
    public Author create(String name, String surname, String country) {
        if(name==null || name.isEmpty())
        {
            throw new IllegalArgumentException();
        }
        this.authorRepository.deleteByName(name);
        Author a=new Author(name, surname,country);
        authorRepository.save(a);
        return a;
    }

    @Override
    @Transactional
    public Author update(Long id,String name, String surname, String country) {
//        if(name==null || name.isEmpty())
//        {
//            throw new IllegalArgumentException();
//        }
        Author a=this.findById(id).orElseThrow(InvalidAuthorIdException::new);
        a.setName(name);
        a.setSurname(surname);
        a.setCountry(country);
        return this.authorRepository.save(a);
    }

    @Override
    public void deleteById(Long id) {
        this.authorRepository.deleteById(id);
    }
}
