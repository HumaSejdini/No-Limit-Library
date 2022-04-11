package mk.ukim.finki.wp.wpelibrary.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ItemNotFoundException extends RuntimeException{
    public ItemNotFoundException(Long id) {
        super(String.format("Item with id: %d was not found",id));
    }
}
