package mk.ukim.finki.webprogramiranje.model;

import lombok.Data;

@Data
public class User {
    private String username;
    private String password;
    private String name;
    private String lastname;

    public User(String username, String password, String name, String lastname) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.lastname = lastname;
    }
}
