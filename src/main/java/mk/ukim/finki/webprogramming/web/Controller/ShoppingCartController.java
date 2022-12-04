package mk.ukim.finki.webprogramming.web.Controller;

import mk.ukim.finki.webprogramming.model.ShoppingCart;
import mk.ukim.finki.webprogramming.model.User;
import mk.ukim.finki.webprogramming.service.ShoppingCartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/shopping-cart")
public class ShoppingCartController  {
    private final ShoppingCartService shoppingCartService;

    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    //prikazuvanje na products u kosnickata za daden user
    @GetMapping
    public String getShoppingCartPage(@RequestParam(required = false) String error, HttpServletRequest request, Model model){
        if(error!= null && !error.isEmpty()){
            model.addAttribute("hasError",true);
            model.addAttribute("error", error);
        }
        User user = (User) request.getSession().getAttribute("user");
        ShoppingCart shoppingCart = this.shoppingCartService.getActiveShoppingCart(user.getUsername());
        model.addAttribute("products", this.shoppingCartService.listAllProductsInShoppingCart(shoppingCart.getId()));
        return "shopping-cart";
    }
    @PostMapping("/add-product/{id}")
    public String addProductToShoppingCart(@PathVariable Long id, HttpServletRequest request){
        try{
            User user = (User) request.getSession().getAttribute("user");
            ShoppingCart shoppingCart = shoppingCartService.addProductToShoppingCart(user.getUsername(), id);
            return "redirect:/shopping-cart";
        }catch (RuntimeException exception){
            return "redirect:/shopping-cart?error="+ exception.getMessage();
        }
    }
}
