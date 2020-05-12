package dinoco.kursova.bd.repository;

import dinoco.kursova.bd.model.MealCooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealCookingRepository extends JpaRepository<MealCooking, Integer> {
}
