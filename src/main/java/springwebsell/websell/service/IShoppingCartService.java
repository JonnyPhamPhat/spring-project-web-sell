package springwebsell.websell.service;

import springwebsell.websell.model.Customer;
import springwebsell.websell.model.Laptop;
import springwebsell.websell.model.ShoppingCart;

public interface IShoppingCartService {
    ShoppingCart addItemToCart(Laptop laptop, int quantity, Customer customer);
}
