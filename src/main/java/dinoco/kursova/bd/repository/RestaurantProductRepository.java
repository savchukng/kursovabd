package dinoco.kursova.bd.repository;

import dinoco.kursova.bd.model.RestaurantProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantProductRepository extends JpaRepository<RestaurantProduct, Integer> {

    List<RestaurantProduct> findAllByBatch_SupplyOrderProduct_SupplyOrder_Restaurant_Id(Integer id);

    List<RestaurantProduct> findAllByBatch_SupplyOrderProduct_SupplyOrder_Restaurant_Id_AndBatch_SupplyOrderProduct_Product_Id(Integer restaurantId, Integer productId);

    List<RestaurantProduct> findAllByBatch_SupplyOrderProduct_Product_IdOrderByBatch_ExpirationDateAsc(Integer productId);
}
