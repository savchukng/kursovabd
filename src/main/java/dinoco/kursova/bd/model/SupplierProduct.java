package dinoco.kursova.bd.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class SupplierProduct {

    @Id
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "SupplierId", referencedColumnName = "Id")
    private Supplier supplier;
    @ManyToOne
    @JoinColumn(name = "ProductId", referencedColumnName = "Id")
    private Product product;

    private Integer price;

}
