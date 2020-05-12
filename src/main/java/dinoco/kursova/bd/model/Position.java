package dinoco.kursova.bd.model;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Position {

    @Id
    private Integer id;

    private String title;

    private Integer salary;

    private String role;

}
