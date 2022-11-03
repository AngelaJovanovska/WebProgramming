package mk.ukim.finki.webprogramiranje.bootstrap;

import mk.ukim.finki.webprogramiranje.model.Category;
import mk.ukim.finki.webprogramiranje.model.User;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

//Anotacija za da se instancira na pocetok, koga kje startuva app.
@Component
public class DataHolder {
    public static List<Category> categories = new ArrayList<>();
    public static List<User> users = new ArrayList<>();

//Anotacija i tuka bidejki init metodot sam po sebe ne se povikuva
@PostConstruct
    public void init(){
        categories.add(new Category("Books","Books Category"));
        categories.add(new Category("Software", "Software Category"));
        users.add(new User("angie","pass","Angela","Jovanovska"));
        users.add(new User("pangie","ssap","Pangela","Johnson"));
    }


}
