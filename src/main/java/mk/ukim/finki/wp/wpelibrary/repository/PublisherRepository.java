package mk.ukim.finki.wp.wpelibrary.repository;

import mk.ukim.finki.wp.wpelibrary.model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher,Long> {
    Publisher findByName(String name);

    void deleteByName(String name);
}
