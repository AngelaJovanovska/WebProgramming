package mk.ukim.finki.webprogramiranje.model.exceptions;

public class PasswordNotMatchException extends RuntimeException{
    public PasswordNotMatchException(){
        super("Password do not match exception");
    }
}