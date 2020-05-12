package dinoco.kursova.bd.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "Batche")
@Data
public class Batch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "SupplyOrderProductId", referencedColumnName = "Id")
    private SupplyOrderProduct supplyOrderProduct;

    private Integer amount;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate expirationDate;


}
