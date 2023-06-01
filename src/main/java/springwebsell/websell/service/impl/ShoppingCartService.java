package springwebsell.websell.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springwebsell.websell.model.CartItem;
import springwebsell.websell.model.Customer;
import springwebsell.websell.model.Laptop;
import springwebsell.websell.model.ShoppingCart;
import springwebsell.websell.repository.CartItemRepository;
import springwebsell.websell.repository.ShoppingCartRepository;
import springwebsell.websell.service.IShoppingCartService;

import javax.persistence.AttributeOverride;
import java.util.HashSet;
import java.util.Set;

@Service
public class ShoppingCartService implements IShoppingCartService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Override
    public ShoppingCart addItemToCart(Laptop laptop, int quantity, Customer customer) {
        ShoppingCart cart = customer.getShoppingCart();
        if(cart == null){
            cart = new ShoppingCart();
        }
        Set<CartItem> cartItems = cart.getCartItems();
        CartItem cartItem = findCartItem(cartItems, laptop.getId());
        if(cartItems == null){
            cartItems = new HashSet<>();
            if(cartItem == null){
                cartItem = new CartItem();
                cartItem.setLaptop(laptop);
                cartItem.setTotalPrice(quantity + laptop.getSalePrice());
                cartItem.setShoppingCart(cart);
                cartItems.add(cartItem);
                cartItemRepository.save(cartItem);
            }
        }else {
            if (cartItem == null) {
                cartItem = new CartItem();
                cartItem.setLaptop(laptop);
                cartItem.setTotalPrice(quantity + laptop.getSalePrice());
                cartItem.setShoppingCart(cart);
                cartItems.add(cartItem);
                cartItemRepository.save(cartItem);
            } else {
                cartItem.setQuantity(cartItem.getQuantity() + quantity);
                cartItem.setTotalPrice(cartItem.getTotalPrice() + (quantity * laptop.getId()));
                cartItemRepository.save(cartItem);
            }
            cart.setCartItems(cartItems);
            int totalItems = totalItems(cart.getCartItems());
            double totalPrice = totalPrice(cart.getCartItems());
            cart.setTotalPrice(totalPrice);
            cart.setTotalItem(totalItems);
            cart.setCustomer(customer);
            return shoppingCartRepository.save(cart);
        }
        return null;
    }

    private CartItem findCartItem(Set<CartItem> cartItems, Long laptopId){
        if(cartItems == null){
            return null;
        }
        CartItem cartItem = null;
        for(CartItem item : cartItems){
            if(item.getLaptop().getId() == laptopId){
                cartItem = item;
            }
        }
        return cartItem;
    }

    private int totalItems(Set<CartItem> cartItems){
        int totalItems = 0;
        for(CartItem item : cartItems){
            totalItems += item.getQuantity();
        }
        return totalItems;
    }

    private double totalPrice(Set<CartItem> cartItems){
        double totalPrice = 0;
        for(CartItem item : cartItems){
            totalPrice += item.getTotalPrice();
        }
        return totalPrice;
    }
}
