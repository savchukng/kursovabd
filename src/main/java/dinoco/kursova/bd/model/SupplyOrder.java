package dinoco.kursova.bd.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class SupplyOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDateTime creationTime;
    @ManyToOne
    @JoinColumn(name = "EmployeeId", referencedColumnName = "Id")
    private Employee employee;
    @ManyToOne
    @JoinColumn(name = "RestaurantId", referencedColumnName = "Id")
    private Restaurant restaurant;
    @ManyToOne
    @JoinColumn(name = "SupplierId", referencedColumnName = "Id")
    private Supplier supplier;
    @Transient
    private List<SupplyOrderProduct> supplyOrderProducts;
    @Transient
    private Supply supply;
    @Transient
    private Integer orderPrice;

}
