package mk.ukim.finki.wp.wpelibrary.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
public class ItemAlreadyInShoppingCart extends RuntimeException{
    public ItemAlreadyInShoppingCart(Long id, String username) {
        super(String.format("Item with id: %d already exists in shopping cart for user with username %s",id,username));
    }
}
