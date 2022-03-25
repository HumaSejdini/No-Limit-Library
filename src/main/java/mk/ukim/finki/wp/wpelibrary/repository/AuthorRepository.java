package mk.ukim.finki.wp.wpelibrary.repository;

import mk.ukim.finki.wp.wpelibrary.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Long> {
}
