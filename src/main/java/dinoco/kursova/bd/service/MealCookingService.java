package dinoco.kursova.bd.service;

import dinoco.kursova.bd.model.ClientOrderMeal;
import dinoco.kursova.bd.model.MealCooking;
import dinoco.kursova.bd.model.MealProduct;
import dinoco.kursova.bd.repository.MealCookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MealCookingService {

    private final MealCookingRepository mealCookingRepository;
    private final EmployeeService employeeService;
    private final CookingProductService cookingProductService;

    public void add(ClientOrderMeal clientOrderMeal, Integer employeeId) {
        for (int currentQuantity = clientOrderMeal.getQuantity(); currentQuantity > 0; currentQuantity--) {
            MealCooking mealCooking = mealCookingRepository.save(MealCooking.builder()
                    .clientOrderMeal(clientOrderMeal)
                    .chef(employeeService.get(employeeId))
                    .time(LocalDateTime.now())
                    .build());
            List<MealProduct> mealProducts = clientOrderMeal.getMeal().getMealProducts();
            mealProducts.forEach(mealProduct -> cookingProductService.add(mealProduct, mealCooking));
        }
    }

}