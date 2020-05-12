package dinoco.kursova.bd.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CookingProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "RestaurantProductId", referencedColumnName = "Id")
    private RestaurantProduct restaurantProduct;

    @ManyToOne
    @JoinColumn(name = "MealCookingId", referencedColumnName = "Id")
    private MealCooking mealCooking;

    private Integer amount;

}
