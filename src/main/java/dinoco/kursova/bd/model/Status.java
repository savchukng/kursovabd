package dinoco.kursova.bd.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "Statuse")
@Data
public class Status {

    @Id
    private Integer id;

    private String name;

}
