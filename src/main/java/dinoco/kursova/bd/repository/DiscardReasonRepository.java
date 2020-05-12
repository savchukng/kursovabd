package dinoco.kursova.bd.repository;

import dinoco.kursova.bd.model.DiscardReason;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscardReasonRepository extends JpaRepository<DiscardReason, Integer> {
}
