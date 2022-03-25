package mk.ukim.finki.wp.wpelibrary.model.exception;

public class PublisherIdNotFoundException extends RuntimeException{
    public PublisherIdNotFoundException(Long id) {
        super(String.format("User with id: %d was not found found",id));
    }
}
