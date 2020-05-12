package dinoco.kursova.bd.repository;

import dinoco.kursova.bd.model.Batch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BatchRepository extends JpaRepository<Batch, Integer> {

    List<Batch> findAllBySupplyOrderProduct_Id(Integer id);

}
