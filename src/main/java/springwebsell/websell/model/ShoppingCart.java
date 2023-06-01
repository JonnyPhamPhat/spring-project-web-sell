package springwebsell.websell.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "shoppingcart")
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_shopping_cart")
    private Long id;
    private int totalItem;
    private double totalPrice;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_customer", referencedColumnName = "id_customer")
    private Customer customer;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shoppingCart")
    private Set<CartItem> cartItems;
}
