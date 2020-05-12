package dinoco.kursova.bd.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Restaurant {

    @Id
    private Integer id;

    private String address;

}
