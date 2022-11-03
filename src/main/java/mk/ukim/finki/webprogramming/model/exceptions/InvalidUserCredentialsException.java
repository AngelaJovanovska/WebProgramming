package mk.ukim.finki.webprogramiranje.model.exceptions;

import mk.ukim.finki.webprogramiranje.repository.InMemoryUserRepository;

public class InvalidUserCredentialsException extends RuntimeException{
    public InvalidUserCredentialsException(){
        super("Invalid user credentials exception");
    }
}
