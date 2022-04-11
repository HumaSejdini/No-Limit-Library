package mk.ukim.finki.wp.wpelibrary.model;

import lombok.Data;
import mk.ukim.finki.wp.wpelibrary.model.enumerations.ShoppingCartEnumStatus;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dateCreated;

    @ManyToOne
    private User user;

    @ManyToMany
    private List<Item> items;

    @Enumerated(EnumType.STRING)
    private ShoppingCartEnumStatus status;

    public ShoppingCart() {
    }

    public ShoppingCart(User user) {
        this.dateCreated = LocalDateTime.now();
        this.user = user;
        this.items = new ArrayList<>();
        this.status = ShoppingCartEnumStatus.CREATED;
    }
}
