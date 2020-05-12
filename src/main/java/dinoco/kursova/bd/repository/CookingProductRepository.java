package dinoco.kursova.bd.repository;

import dinoco.kursova.bd.model.CookingProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CookingProductRepository extends JpaRepository<CookingProduct, Integer> {
}
