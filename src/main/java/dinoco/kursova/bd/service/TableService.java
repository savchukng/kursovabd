package dinoco.kursova.bd.service;

import dinoco.kursova.bd.model.Table;
import dinoco.kursova.bd.repository.TableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TableService {

    private final TableRepository tableRepository;

    public List<Table> getByRestaurantId(Integer id) {
        return tableRepository.findAllByRestaurant_Id(id);
    }

}
