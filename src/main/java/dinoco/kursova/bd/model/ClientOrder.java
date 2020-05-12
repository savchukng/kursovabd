package dinoco.kursova.bd.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDateTime orderDate;
    @ManyToOne
    @JoinColumn(name = "TableId", referencedColumnName = "Id")
    private Table table;
    @ManyToOne
    @JoinColumn(name = "WaiterId", referencedColumnName = "Id")
    private Employee waiter;

    private Integer sum;

    private Boolean paymentStatus;


}
