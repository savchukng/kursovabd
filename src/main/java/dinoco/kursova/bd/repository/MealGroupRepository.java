package dinoco.kursova.bd.repository;

import dinoco.kursova.bd.model.MealGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealGroupRepository extends JpaRepository<MealGroup, Integer> {
}
