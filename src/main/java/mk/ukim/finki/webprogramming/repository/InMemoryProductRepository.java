package mk.ukim.finki.webprogramming.repository;

import mk.ukim.finki.webprogramming.bootstrap.DataHolder;
import mk.ukim.finki.webprogramming.model.Category;
import mk.ukim.finki.webprogramming.model.Manufacturer;
import mk.ukim.finki.webprogramming.model.Product;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryProductRepository {
    public List<Product> findAll(){
        return DataHolder.products;
    }
    public Optional<Product> findById(Long id){
        return DataHolder.products.stream().filter(product -> product.getId().equals(id)).findFirst();
    }

    public Optional<Product> save(String name, Double price, Integer quantity, Category category, Manufacturer manufacturer){
        DataHolder.products.removeIf(product -> product.getName().equals(name));
        Product product = new Product(name,price,quantity,category,manufacturer);
        DataHolder.products.add(product);
        return Optional.of(product);
    }

    public void deleteById(Long id){
        DataHolder.products.removeIf(product -> product.getId().equals(id));
    }
    public Optional<Product> findByName(String name){
        return DataHolder.products.stream().filter(product -> product.getName().equals(name)).findFirst();
    }
}
