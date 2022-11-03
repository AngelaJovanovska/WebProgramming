package mk.ukim.finki.webprogramiranje.Service;

import mk.ukim.finki.webprogramiranje.model.User;

public interface AuthService {
    User login(String username, String password);
    User register(String username, String password, String repeatPassword, String name, String surname);

}
