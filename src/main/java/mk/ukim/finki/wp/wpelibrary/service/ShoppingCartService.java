package mk.ukim.finki.wp.wpelibrary.service;



import mk.ukim.finki.wp.wpelibrary.model.Item;
import mk.ukim.finki.wp.wpelibrary.model.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {
    //da vrati site produkti vo edna koshnicka
    List<Item> listAllItemsInShoppingCart(Long cartId);
    //ona koshnicka koja sto vo momentot e aktivna
    ShoppingCart getActiveShoppingCart(String username);
    ShoppingCart addItemToShoppingCart(String username, Long productId);
}
