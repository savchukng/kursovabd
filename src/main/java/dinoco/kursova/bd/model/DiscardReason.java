package dinoco.kursova.bd.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class DiscardReason {

    @Id
    private Integer id;

    private String reason;

}
