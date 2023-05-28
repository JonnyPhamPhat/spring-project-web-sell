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
@Table(name = "laptop")
public class Laptop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_laptop")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "drive")
    private String drive;
    @Column(name = "screen")
    private String screen;
    @Column(name = "card")
    private String card;
    @Column(name = "operatingsystem")
    private String operatingSystem;
    private double salePrice;
    private int currentQuantity;
    @Transient
    private MultipartFile images;
    private String filename;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_category", referencedColumnName = "id_category")
    private Category category;
    private boolean is_deleted;
    private boolean is_activated;

    public Laptop(String name){
        this.name = name;
        this.is_activated = true;
        this.is_deleted = false;
    }
}
