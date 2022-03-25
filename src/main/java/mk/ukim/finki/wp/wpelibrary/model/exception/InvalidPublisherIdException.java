package mk.ukim.finki.wp.wpelibrary.model.exception;

public class InvalidPublisherIdException extends RuntimeException{
    public InvalidPublisherIdException() {
        super("Publisher id not found !");
    }
}
