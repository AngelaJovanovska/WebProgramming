package mk.ukim.finki.webprogramming.model.exceptions;

public class PasswordNotMatchException extends RuntimeException{
    public PasswordNotMatchException(){
        super("Password do not match exception");
    }
}