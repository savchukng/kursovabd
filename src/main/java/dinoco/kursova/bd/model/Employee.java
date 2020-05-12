package dinoco.kursova.bd.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Data
@Entity
public class Employee {

    @Id
    private Integer id;

    private String firstName;

    private String lastName;

    private String phone;

    private LocalDate dateOfBirth;

    private String password;

    private LocalDate hireDate;
    @ManyToOne
    @JoinColumn(name = "RestaurantId", referencedColumnName = "Id")
    private Restaurant restaurant;
    @ManyToOne
    @JoinColumn(name = "PositionId", referencedColumnName = "Id")
    private Position position;

}
