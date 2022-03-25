package mk.ukim.finki.wp.wpelibrary.service.impl;

import mk.ukim.finki.wp.wpelibrary.model.Author;
import mk.ukim.finki.wp.wpelibrary.repository.AuthorRepository;
import mk.ukim.finki.wp.wpelibrary.service.AuthorService;
import org.springframework.stereotype.Service;

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
    public Author create(String name, String surname, String country) {
        if(name==null || name.isEmpty())
        {
            throw new IllegalArgumentException();
        }
        Author a=new Author(name, surname,country);
        authorRepository.save(a);
        return a;
    }

    @Override
    public Author update(String name, String surname, String country) {
        if(name==null || name.isEmpty())
        {
            throw new IllegalArgumentException();
        }
        Author a=new Author(name, surname,country);
        authorRepository.save(a);
        return a;
    }

    @Override
    public void deleteById(Long id) {
        this.authorRepository.deleteById(id);
    }
}
