package mk.ukim.finki.webprogramiranje.repository;

import mk.ukim.finki.webprogramiranje.bootstrap.DataHolder;
import mk.ukim.finki.webprogramiranje.model.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class InMemoryUserRepository {
    public Optional<User> findByUsername(String username){
        return DataHolder.users.stream().filter(users->users.getUsername().equals(username)).findFirst();
    }

    public Optional<User> findUsernameAndPassword(String username,String pass){
        return DataHolder.users.stream().filter(user -> user.getUsername().equals(username) && user.getPassword().equals(pass)).findFirst();
    }

    public User saveOrUpdate(User user){
        DataHolder.users.removeIf(r->r.getUsername().equals(user.getUsername()));
        DataHolder.users.add(user);
        return user;
    }
    public void delete(String username){
        DataHolder.users.removeIf(r->r.getUsername().equals(username));
    }

}
