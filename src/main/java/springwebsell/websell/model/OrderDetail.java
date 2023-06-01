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
@Table(name = "orderdetail")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_orderdetail")
    private Long id;
    private int quantity;
    @Column(name = "totalprice")
    private double totalPrice;
    @Column(name = "unitprice")
    private double unitPrice;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_order", referencedColumnName = "id_order")
    private Order order;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_laptop", referencedColumnName = "id_laptop")
    private Laptop laptop;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_phone", referencedColumnName = "id_phone")
    private SmartPhone smartPhone;
}
