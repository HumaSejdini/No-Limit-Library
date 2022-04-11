package mk.ukim.finki.wp.wpelibrary.repository;

import mk.ukim.finki.wp.wpelibrary.model.ShoppingCart;
import mk.ukim.finki.wp.wpelibrary.model.User;
import mk.ukim.finki.wp.wpelibrary.model.enumerations.ShoppingCartEnumStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart,Long> {
    Optional<ShoppingCart> findByUserAndStatus(User user, ShoppingCartEnumStatus status);
}
