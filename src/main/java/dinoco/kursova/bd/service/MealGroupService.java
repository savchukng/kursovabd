package dinoco.kursova.bd.service;

import dinoco.kursova.bd.model.MealGroup;
import dinoco.kursova.bd.repository.MealGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MealGroupService {

    private final MealGroupRepository mealGroupRepository;

    public List<MealGroup> getAll() {
        return mealGroupRepository.findAll();
    }

}
