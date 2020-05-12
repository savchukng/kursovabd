package dinoco.kursova.bd.repository;

import dinoco.kursova.bd.model.ClientOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientOrderRepository extends JpaRepository<ClientOrder, Integer> {

    List<ClientOrder> findAllByPaymentStatus(Boolean paymentStatus);

}
