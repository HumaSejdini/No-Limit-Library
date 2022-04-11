package mk.ukim.finki.wp.wpelibrary.service.impl;


import mk.ukim.finki.wp.wpelibrary.model.Item;
import mk.ukim.finki.wp.wpelibrary.model.ShoppingCart;
import mk.ukim.finki.wp.wpelibrary.model.User;
import mk.ukim.finki.wp.wpelibrary.model.enumerations.ShoppingCartEnumStatus;
import mk.ukim.finki.wp.wpelibrary.model.exception.ItemAlreadyInShoppingCart;
import mk.ukim.finki.wp.wpelibrary.model.exception.ItemNotFoundException;
import mk.ukim.finki.wp.wpelibrary.model.exception.ShoppingCartNotFoundException;
import mk.ukim.finki.wp.wpelibrary.model.exception.UserNotFoundException;
import mk.ukim.finki.wp.wpelibrary.repository.ItemRepository;
import mk.ukim.finki.wp.wpelibrary.repository.ShoppingCartRepository;
import mk.ukim.finki.wp.wpelibrary.repository.UserRepository;
import mk.ukim.finki.wp.wpelibrary.service.ShoppingCartService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;

    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository,
                                   UserRepository userRepository,
                                   ItemRepository itemRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.userRepository = userRepository;
        this.itemRepository=itemRepository;

    }

    @Override
    public List<Item> listAllItemsInShoppingCart(Long cartId) {
        //dali postoi koshnickata
        if (!this.shoppingCartRepository.findById(cartId).isPresent())
            throw new ShoppingCartNotFoundException(cartId);
        return this.shoppingCartRepository.findById(cartId).get().getItems();
    }

    @Override
    public ShoppingCart getActiveShoppingCart(String username) {

        User user = this.userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));

       return this.shoppingCartRepository.findByUserAndStatus(user, ShoppingCartEnumStatus.CREATED)
                .orElseGet(()-> {
                    ShoppingCart cart = new ShoppingCart(user);
                    return this.shoppingCartRepository.save(cart);
                });
    }

    @Override
    public ShoppingCart addItemToShoppingCart(String username, Long itemId) {
        ShoppingCart shoppingCart = this.getActiveShoppingCart(username);
        Item product =this.itemRepository.findById(itemId)
                .orElseThrow(()->new ItemNotFoundException(itemId));
        if(shoppingCart.getItems().stream().filter(i->i.getId().equals(itemId))
                .collect(Collectors.toList()).size()>0)throw new ItemAlreadyInShoppingCart(itemId,username);
        shoppingCart.getItems().add(product);
        return this.shoppingCartRepository.save(shoppingCart);
    }
}
