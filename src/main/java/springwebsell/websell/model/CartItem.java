package springwebsell.websell.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "cartitem")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cart")
    private Long id;
    private int quantity;
    @Column(name = "totalprice")
    private double totalPrice;
    @Column(name = "unitprice")
    private double unitPrice;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_shopping_cart", referencedColumnName = "id_shopping_cart")
    private ShoppingCart shoppingCart;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_laptop", referencedColumnName = "id_laptop")
    private Laptop laptop;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_phone", referencedColumnName = "id_phone")
    private SmartPhone smartPhone;
}
