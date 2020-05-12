package dinoco.kursova.bd.service;

import dinoco.kursova.bd.model.RestaurantProduct;
import dinoco.kursova.bd.repository.RestaurantProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class RestaurantProductService {

    private final RestaurantProductRepository restaurantProductRepository;

    public void save(RestaurantProduct restaurantProduct) {
        restaurantProductRepository.save(restaurantProduct);
    }

    public List<RestaurantProduct> getByRestaurantId(Integer id) {
        return restaurantProductRepository.findAllByBatch_SupplyOrderProduct_SupplyOrder_Restaurant_Id(id);
    }

    public List<RestaurantProduct> getByRestaurantIdAndProductId(Integer restaurantId, Integer productId) {
        List<RestaurantProduct> restaurantProducts = restaurantProductRepository.findAllByBatch_SupplyOrderProduct_SupplyOrder_Restaurant_Id_AndBatch_SupplyOrderProduct_Product_Id(restaurantId, productId);
        restaurantProducts.forEach(restaurantProduct -> {
            Integer activeRestaurantProductId = restaurantProduct.getBatch().getSupplyOrderProduct().getProduct().getRestaurantProductId();
            if (Objects.nonNull(activeRestaurantProductId) && activeRestaurantProductId.equals(restaurantProduct.getId())) {
                restaurantProduct.setActive(true);
            }
        });
        return restaurantProducts;
    }

    public RestaurantProduct get(Integer id) {
        return restaurantProductRepository.getOne(id);
    }

    public Integer findOldestRestaurantProductId(Integer productId, Integer oldRestaurantProduct) {
        return restaurantProductRepository.findAllByBatch_SupplyOrderProduct_Product_IdOrderByBatch_ExpirationDateAsc(productId).stream()
                .filter(restaurantProduct -> !restaurantProduct.getId().equals(oldRestaurantProduct))
                .findFirst()
                .get()
                .getId();
    }

}
