package mk.ukim.finki.wp.wpelibrary.model.exception;

public class InvalidItemIdException extends RuntimeException{
    public InvalidItemIdException() {
        super("Item id not found !");
    }
}

