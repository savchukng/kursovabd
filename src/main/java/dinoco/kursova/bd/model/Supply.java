package dinoco.kursova.bd.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity(name = "Supplie")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Supply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDateTime deliveryTime;
    @ManyToOne
    @JoinColumn(name = "responsibleEmployeeId", referencedColumnName = "Id")
    private Employee responsibleEmployee;
    @OneToOne
    @JoinColumn(name = "supplyOrderId", referencedColumnName = "Id")
    private SupplyOrder supplyOrder;

}
