package mk.ukim.finki.wp.wpelibrary.repository;

import mk.ukim.finki.wp.wpelibrary.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository  extends JpaRepository<Item,Long> {

    Optional<Item> findByTitle(String title);
    void deleteByTitle(String title);
    @Query("SELECT i from Item i where i.title like %?1%")
    List<Item> findAllByTitle(String title);

}