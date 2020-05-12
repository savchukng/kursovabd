package dinoco.kursova.bd.repository;

import dinoco.kursova.bd.model.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TableRepository extends JpaRepository<Table, Integer> {

    List<Table> findAllByRestaurant_Id(Integer id);

}
