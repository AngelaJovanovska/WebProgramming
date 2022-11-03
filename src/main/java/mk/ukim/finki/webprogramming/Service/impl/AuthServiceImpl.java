package mk.ukim.finki.webprogramiranje.Service.impl;

import mk.ukim.finki.webprogramiranje.Service.AuthService;
import mk.ukim.finki.webprogramiranje.model.User;
import mk.ukim.finki.webprogramiranje.model.exceptions.InvalidArgumentException;
import mk.ukim.finki.webprogramiranje.model.exceptions.InvalidUserCredentialsException;
import mk.ukim.finki.webprogramiranje.model.exceptions.PasswordNotMatchException;
import mk.ukim.finki.webprogramiranje.repository.InMemoryUserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final InMemoryUserRepository userRepository;

    public AuthServiceImpl(InMemoryUserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public User login(String username, String password) {
        if(username == null || username.isEmpty() || password == null || password.isEmpty()){
            throw new InvalidArgumentException();
        }
        return userRepository.findUsernameAndPassword(username, password).orElseThrow(InvalidUserCredentialsException::new);
    }

    @Override
    public User register(String username, String password, String repeatPassword, String name, String surname) {
        if(username == null || username.isEmpty() || password ==null || password.isEmpty()){
            throw new InvalidArgumentException();
        }
        if(!password.equals(repeatPassword)){
            throw new PasswordNotMatchException();
        }
        User user = new User(username,password, name, surname);
        return userRepository.saveOrUpdate(user);
    }
}
