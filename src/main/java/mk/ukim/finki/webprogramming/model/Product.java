package mk.ukim.finki.webprogramiranje.model;

import lombok.Data;

@Data
public class Product {
    private Long id;
    private String name;
    private Double price;
    private Integer quantity;
    private Category category;
    private Manufactorer manufactorer;

    public Product(String name, Double price, Integer quantity, Category category, Manufactorer manufactorer) {
        this.id = (long) (Math.random()*1000);
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.manufactorer = manufactorer;
    }
}
