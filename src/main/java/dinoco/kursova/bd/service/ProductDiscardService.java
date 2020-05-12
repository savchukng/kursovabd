package dinoco.kursova.bd.service;

import dinoco.kursova.bd.model.ProductDiscard;
import dinoco.kursova.bd.model.RestaurantProduct;
import dinoco.kursova.bd.repository.ProductDiscardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductDiscardService {

    private final ProductDiscardRepository productDiscardRepository;
    private final RestaurantProductService restaurantProductService;

    public ProductDiscard add(ProductDiscard productDiscard) {
        RestaurantProduct restaurantProduct = restaurantProductService.get(productDiscard.getRestaurantProduct().getId());
        restaurantProduct.setAmount(restaurantProduct.getAmount() - productDiscard.getAmount());
        restaurantProductService.save(restaurantProduct);
        productDiscard.setDiscardDate(LocalDate.now());
        return productDiscardRepository.save(productDiscard);
    }

    public List<ProductDiscard> getByRestaurantId(Integer restaurantId) {
        return productDiscardRepository.findAllByRestaurantProduct_Batch_SupplyOrderProduct_SupplyOrder_Restaurant_Id(restaurantId);
    }

}
