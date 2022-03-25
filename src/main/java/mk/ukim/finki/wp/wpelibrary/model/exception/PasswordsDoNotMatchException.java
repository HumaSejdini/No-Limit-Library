package mk.ukim.finki.wp.wpelibrary.model.exception;

public class PasswordsDoNotMatchException extends RuntimeException{
    public PasswordsDoNotMatchException(){
        super("Password do not match exception");
    }
}
