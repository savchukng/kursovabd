package dinoco.kursova.bd.service;

import dinoco.kursova.bd.model.*;
import dinoco.kursova.bd.repository.CookingProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CookingProductService {

    private final CookingProductRepository cookingProductRepository;
    private final RestaurantProductService restaurantProductService;
    private final ProductService productService;

    public void add(MealProduct mealProduct, MealCooking mealCooking) {
        int neededAmount = mealProduct.getAmount();
        while (neededAmount > 0) {
            RestaurantProduct restaurantProduct = restaurantProductService.get(mealProduct.getProduct().getRestaurantProductId());
            Integer cookingProductAmount;
            if (neededAmount > restaurantProduct.getAmount()) {
                cookingProductAmount = restaurantProduct.getAmount();
                restaurantProduct.setAmount(0);
                neededAmount -= restaurantProduct.getAmount();
                productService.changeRestaurantProduct(mealProduct.getProduct().getId(), restaurantProduct.getId());
            } else {
                cookingProductAmount = neededAmount;
                restaurantProduct.setAmount(restaurantProduct.getAmount() - neededAmount);
                neededAmount = 0;
            }
            restaurantProductService.save(restaurantProduct);
            if (cookingProductAmount != 0) {
                CookingProduct cookingProduct = CookingProduct.builder()
                        .mealCooking(mealCooking)
                        .amount(cookingProductAmount)
                        .restaurantProduct(restaurantProduct)
                        .build();
                cookingProductRepository.save(cookingProduct);
            }
        }
    }

}
