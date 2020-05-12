package dinoco.kursova.bd.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String description;

    @ManyToOne
    @JoinColumn(name = "AmountTypeId", referencedColumnName = "Id")
    private AmountType amountType;

    private Integer restaurantProductId;
}
