package springwebsell.websell.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import springwebsell.websell.model.Customer;
import springwebsell.websell.model.ShoppingCart;
import springwebsell.websell.service.ICustomerService;
import springwebsell.websell.service.IShoppingCartService;

import java.security.Principal;

@Controller
public class CartController {

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private IShoppingCartService shoppingCartService;

    @GetMapping("/cart")
    public String DisplayCartPage(Model model, Principal principal){
        String username = principal.getName();
        Customer customer = customerService.findByUsername(username);
        ShoppingCart shoppingCart = customer.getShoppingCart();
        model.addAttribute("shoppingCart", shoppingCart);
        return "cart/cart";
    }

}
