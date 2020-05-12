package dinoco.kursova.bd.repository;

import dinoco.kursova.bd.model.Supply;
import dinoco.kursova.bd.model.SupplyOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SupplyRepository extends JpaRepository<Supply, Integer> {

    Optional<Supply> findBySupplyOrder(SupplyOrder supplyOrder);

}
