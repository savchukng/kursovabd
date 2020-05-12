package dinoco.kursova.bd.repository;

import dinoco.kursova.bd.model.ProductDiscard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDiscardRepository extends JpaRepository<ProductDiscard, Integer> {

    List<ProductDiscard> findAllByRestaurantProduct_Batch_SupplyOrderProduct_SupplyOrder_Restaurant_Id(Integer id);

}
