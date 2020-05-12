package dinoco.kursova.bd.service;

import dinoco.kursova.bd.exception.NotFoundException;
import dinoco.kursova.bd.model.Meal;
import dinoco.kursova.bd.repository.MealRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MealService {

    private final MealRepository mealRepository;
    private final MealProductService mealProductService;

    public List<Meal> getAll() {
        return mealRepository.findAll();
    }

    public List<Meal> searchMeals(String keyword) {
        return mealRepository.findAllByTitleContains(keyword);
    }

    public Meal get(Integer id) {
        return mealRepository.findById(id).orElseThrow(() -> new NotFoundException("Meal not found"));
    }

    public Integer save(Meal meal) {
        if (Objects.isNull(meal.getMealProducts()) && Objects.nonNull(meal.getId())) {
            meal.setMealProducts(get(meal.getId()).getMealProducts());
        }
        return mealRepository.save(meal).getId();
    }

    public void delete(Integer id) {
        mealProductService.deleteByMealId(id);
        mealRepository.deleteById(id);
    }
}
