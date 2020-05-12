package dinoco.kursova.bd.repository;

import dinoco.kursova.bd.model.MealProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MealProductRepository extends JpaRepository<MealProduct, Integer> {

    List<MealProduct> findAllByMeal_Id(Integer mealId);

}
