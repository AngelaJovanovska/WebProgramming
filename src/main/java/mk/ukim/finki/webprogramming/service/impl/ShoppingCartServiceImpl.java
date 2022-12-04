package mk.ukim.finki.webprogramming.service.impl;

import mk.ukim.finki.webprogramming.model.Product;
import mk.ukim.finki.webprogramming.model.ShoppingCart;
import mk.ukim.finki.webprogramming.model.User;
import mk.ukim.finki.webprogramming.model.enumeration.ShoppingCartStatus;
import mk.ukim.finki.webprogramming.model.exceptions.ProductAlreadyInShoppingCartException;
import mk.ukim.finki.webprogramming.model.exceptions.ProductNotFoundException;
import mk.ukim.finki.webprogramming.model.exceptions.ShoppingCartNotFoundException;
import mk.ukim.finki.webprogramming.model.exceptions.UserNotFoundException;
import mk.ukim.finki.webprogramming.repository.InMemoryProductRepository;
import mk.ukim.finki.webprogramming.repository.InMemoryShoppingCartRepository;
import mk.ukim.finki.webprogramming.repository.InMemoryUserRepository;
import mk.ukim.finki.webprogramming.service.ProductService;
import mk.ukim.finki.webprogramming.service.ShoppingCartService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final InMemoryShoppingCartRepository shoppingCartRepository;
    private final InMemoryUserRepository inMemoryUserRepository;
    private final ProductService productService;
    private final InMemoryProductRepository inMemoryProductRepository;

    public ShoppingCartServiceImpl(InMemoryShoppingCartRepository shoppingCartRepository, InMemoryUserRepository inMemoryUserRepository, ProductService productService, InMemoryProductRepository inMemoryProductRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.inMemoryUserRepository = inMemoryUserRepository;
        this.productService = productService;
        this.inMemoryProductRepository = inMemoryProductRepository;
    }

    @Override
    public List<Product> listAllProductsInShoppingCart(Long cartId) {
        if(!this.shoppingCartRepository.findById(cartId).isPresent()){
            throw new ShoppingCartNotFoundException(cartId);
        }
        return shoppingCartRepository.findById(cartId).get().getProducts();
    }

    @Override
    public ShoppingCart getActiveShoppingCart(String username) {
        return shoppingCartRepository.findByUsernameAndStatus(username, ShoppingCartStatus.CREATED)
                .orElseGet(() ->{
                    User user = this.inMemoryUserRepository.findByUsername(username)
                            .orElseThrow(()-> new UserNotFoundException(username));
                    ShoppingCart shoppingCart = new ShoppingCart(user);
                    return  shoppingCartRepository.save(shoppingCart);
                });
    }

    @Override
    public ShoppingCart addProductToShoppingCart(String username, Long productId) {
        ShoppingCart shoppingCart = this.getActiveShoppingCart(username);
        Product product = this.productService.findById(productId).orElseThrow(()->
                new ProductNotFoundException(productId));
        //vidi ovoj pa 25min
        if(shoppingCart.getProducts().stream().filter(product1 ->
                product1.getId().equals(productId))
                .collect(Collectors.toList()).size() > 0){
            throw new ProductAlreadyInShoppingCartException(productId,username);
        }else{
            shoppingCart.getProducts().add(product);
            return shoppingCartRepository.save(shoppingCart);
        }

    }
    //raboti i ovaka
//        ShoppingCart shoppingCart = this.getActiveShoppingCart(username);
//        Product product = this.inMemoryProductRepository.findById(productId).orElseThrow(()->
//                new ProductNotFoundException(productId));
//        //vidi ovoj pa 25min
//        if(shoppingCart.getProducts().stream().filter(product1 ->
//                        product1.getId().equals(productId))
//                .collect(Collectors.toList()).size() > 0){
//            throw new ProductAlreadyInShoppingCartException(productId,username);
//        }else{
//            shoppingCart.getProducts().add(product);
//            return shoppingCartRepository.save(shoppingCart);
//        }
//
//    }
}
