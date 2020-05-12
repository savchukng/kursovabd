package dinoco.kursova.bd.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Meal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private Integer price;

    private String description;

    private Integer portionSize;
    @ManyToOne
    @JoinColumn(name = "AmountTypeId", referencedColumnName = "Id")
    private AmountType amountType;
    @ManyToOne
    @JoinColumn(name = "GroupId", referencedColumnName = "Id")
    private MealGroup group;
    @OneToMany(mappedBy = "meal")
    private List<MealProduct> mealProducts;

}
