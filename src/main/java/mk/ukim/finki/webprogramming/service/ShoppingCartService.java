package mk.ukim.finki.webprogramming.service;

import mk.ukim.finki.webprogramming.model.Product;
import mk.ukim.finki.webprogramming.model.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {


    List<Product> listAllProductsInShoppingCart(Long cartId);

    ShoppingCart getActiveShoppingCart(String username);
    ShoppingCart addProductToShoppingCart(String username, Long productId);
}
