package springwebsell.websell.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "smartphone")
public class SmartPhone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_phone")
    private Long id;
    private String name;
    @Column(name = "operatingsystem")
    private String operatingSystem;
    private String chip;
    private String ram;
    private String pin;
    @Column(name = "saleprice")
    private double salePrice;
    @Column(name = "curentquantity")
    private int currentQuantity;
    @Transient
    private MultipartFile images;
    private String filename;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_category", referencedColumnName = "id_category")
    private Category category;

    private boolean is_deleted;
    private boolean is_activated;

    public SmartPhone(String name){
        this.name = name;
        this.is_activated = true;
        this.is_deleted = false;
    }
}
