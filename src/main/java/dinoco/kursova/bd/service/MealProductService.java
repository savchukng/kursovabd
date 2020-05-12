package dinoco.kursova.bd.service;

import dinoco.kursova.bd.exception.NotFoundException;
import dinoco.kursova.bd.model.MealProduct;
import dinoco.kursova.bd.repository.MealProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MealProductService {

    private final MealProductRepository mealProductRepository;

    public MealProduct get(Integer id) {
        return mealProductRepository.findById(id).orElseThrow(() -> new NotFoundException("Meal product is not found"));
    }

    public void save(MealProduct mealProduct) {
        mealProductRepository.save(mealProduct);
    }

    public void update(Integer id, Integer amount) {
        MealProduct mealProduct = get(id);
        mealProduct.setAmount(amount);
        mealProductRepository.save(mealProduct);
    }

    public void delete(Integer id) {
        mealProductRepository.deleteById(id);
    }

    public void deleteByMealId(Integer mealId) {
        List<MealProduct> mealProducts = mealProductRepository.findAllByMeal_Id(mealId);
        mealProducts.forEach(mealProductRepository::delete);
    }

}
