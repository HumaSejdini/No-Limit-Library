package mk.ukim.finki.wp.wpelibrary.model.exception;

public class InvalidAuthorIdException extends RuntimeException{
    public InvalidAuthorIdException() {super("Author id not found !");
    }
}
