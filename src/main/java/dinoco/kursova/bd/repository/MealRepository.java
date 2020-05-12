package dinoco.kursova.bd.repository;

import dinoco.kursova.bd.model.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MealRepository extends JpaRepository<Meal, Integer> {

    List<Meal> findAllByTitleContains(String keyword);

}
