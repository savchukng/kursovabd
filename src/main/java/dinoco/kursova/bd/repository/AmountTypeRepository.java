package dinoco.kursova.bd.repository;

import dinoco.kursova.bd.model.AmountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AmountTypeRepository extends JpaRepository<AmountType, Integer> {
}
