package mk.ukim.finki.webprogramming.bootstrap;

import mk.ukim.finki.webprogramming.model.*;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

//Anotacija za da se instancira na pocetok, koga kje startuva app.
@Component
public class DataHolder {
    public static List<Category> categories = new ArrayList<>();
    public static List<User> users = new ArrayList<>();
    public static List<Manufacturer> manufacturers = new ArrayList<>();
    public static List<Product> products = new ArrayList<>();

    public static List<ShoppingCart>  shoppingCarts = new ArrayList<>();

//Anotacija i tuka bidejki init metodot sam po sebe ne se povikuva

    @PostConstruct
    public void init(){
        categories.add(new Category("Books","Books Category"));
        categories.add(new Category("Software", "Software Category"));
        users.add(new User("angie","pass","Angela","Jovanovska"));
        users.add(new User("pangie","ssap","Pangela","Johnson"));
        Manufacturer manufacturer =  new Manufacturer("Nike","NY NY");
        manufacturers.add(manufacturer);
        manufacturers.add(new Manufacturer("Apple","LA LA"));
        Category category = new Category("Sport","sport");
        categories.add(category);
        products.add(new Product("Ball",235.3,7,category,manufacturer));
        products.add(new Product("Shoes",123.1,3,category,manufacturer));
        products.add(new Product("Hoodie",433.2,10,category,manufacturer));


    }


}
