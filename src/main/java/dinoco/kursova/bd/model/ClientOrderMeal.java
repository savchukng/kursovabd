package dinoco.kursova.bd.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientOrderMeal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "ClientOrderId", referencedColumnName = "Id")
    private ClientOrder clientOrder;

    @ManyToOne
    @JoinColumn(name = "MealId", referencedColumnName = "Id")
    private Meal meal;

    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "ClientOrderStatusId", referencedColumnName = "Id")
    private Status status;

    private String description;

}
