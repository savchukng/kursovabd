package dinoco.kursova.bd.repository;

import dinoco.kursova.bd.model.Supplier;
import dinoco.kursova.bd.model.SupplierProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierProductRepository extends JpaRepository<SupplierProduct, Integer> {

    List<SupplierProduct> findBySupplier(Supplier supplier);

}
