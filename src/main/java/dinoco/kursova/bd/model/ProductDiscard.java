package dinoco.kursova.bd.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDiscard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "RestaurantProductId", referencedColumnName = "Id")
    private RestaurantProduct restaurantProduct;
    @ManyToOne
    @JoinColumn(name = "ReasonId", referencedColumnName = "Id")
    private DiscardReason discardReason;

    private Integer amount;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate discardDate;

}
