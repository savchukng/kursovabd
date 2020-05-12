package dinoco.kursova.bd.repository;

import dinoco.kursova.bd.model.SupplyOrder;
import dinoco.kursova.bd.model.SupplyOrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplyOrderProductRepository extends JpaRepository<SupplyOrderProduct, Integer> {

    List<SupplyOrderProduct> findBySupplyOrder(SupplyOrder supplyOrder);

}
