package mk.ukim.finki.wp.wpelibrary.repository;

import mk.ukim.finki.wp.wpelibrary.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemRepository  extends JpaRepository<Item,Long> {
    Optional<Item> findByTitle(String title);

    void deleteByTitle(String title);
}
