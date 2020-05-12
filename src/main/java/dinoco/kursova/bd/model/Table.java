package dinoco.kursova.bd.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Data
public class Table {

    @Id
    private Integer id;

    private Integer seatsCount;

    private Integer tableNumber;
    @ManyToOne
    @JoinColumn(name = "restaurantId", referencedColumnName = "id")
    private Restaurant restaurant;

}
