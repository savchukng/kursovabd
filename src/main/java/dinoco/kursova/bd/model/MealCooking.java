package dinoco.kursova.bd.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MealCooking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "ChefId", referencedColumnName = "Id")
    private Employee chef;

    @ManyToOne
    @JoinColumn(name = "ClientOrderMealId", referencedColumnName = "Id")
    private ClientOrderMeal clientOrderMeal;

    private LocalDateTime time;

}
