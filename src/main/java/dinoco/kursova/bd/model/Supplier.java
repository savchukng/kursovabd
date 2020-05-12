package dinoco.kursova.bd.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Supplier {

    @Id
    private Integer id;

    private String name;

    private String address;

    private String phone;

}
