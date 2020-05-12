package dinoco.kursova.bd.repository;

import dinoco.kursova.bd.model.ClientOrderMeal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientOrderMealRepository extends JpaRepository<ClientOrderMeal, Integer> {

    List<ClientOrderMeal> findAllByClientOrder_Id(Integer id);

}
