package dinoco.kursova.bd.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class AmountType {

    @Id
    private Integer id;

    @Column(unique = true)
    private String type;

}
