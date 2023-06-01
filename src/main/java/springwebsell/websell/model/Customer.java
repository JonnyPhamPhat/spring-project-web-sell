package springwebsell.websell.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_customer")
    private Long id;
    @Column(name = "firstname")
    private String firstName;
    @Column(name = "lastname")
    private String lastName;
    private String username;
    private String password;
    private String country;
    @Column(name = "phonenumber")
    private String phoneNumber;
    private String address;
    @Transient
    private MultipartFile images;
    private String filename;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_city", referencedColumnName = "id_city")
    private City city;

    @OneToOne(mappedBy = "customer")
    private ShoppingCart shoppingCart;
    @OneToMany(mappedBy = "customer")
    private List<Order> order;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name="customer_role",
            joinColumns = @JoinColumn(name = "id_customer", referencedColumnName = "id_customer"),
            inverseJoinColumns = @JoinColumn(name = "id_role", referencedColumnName = "id_role")
    )
    private Collection<Role> roles;
}
