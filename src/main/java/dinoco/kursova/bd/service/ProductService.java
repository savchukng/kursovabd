package dinoco.kursova.bd.service;

import dinoco.kursova.bd.exception.NotFoundException;
import dinoco.kursova.bd.model.Product;
import dinoco.kursova.bd.model.RestaurantProduct;
import dinoco.kursova.bd.model.SupplyOrderProduct;
import dinoco.kursova.bd.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final RestaurantProductService restaurantProductService;

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public List<Product> searchProducts(String keyword) {
        return productRepository.findAllByNameContains(keyword);
    }

    public Product get(Integer id) {
        return productRepository.findById(id).orElseThrow(() -> new NotFoundException("Product is not found"));
    }

    public void save(Product product) {
        productRepository.save(product);
    }

    public void delete(Integer id) {
        productRepository.deleteById(id);
    }

    public void changeRestaurantProduct(Integer id, Integer oldRestaurantProduct) {
        Product product = get(id);
        product.setRestaurantProductId(restaurantProductService.findOldestRestaurantProductId(id, oldRestaurantProduct));
        productRepository.save(product);
    }

    public SupplyOrderProduct makeActive(Integer restaurantProductId) {
        RestaurantProduct restaurantProduct = restaurantProductService.get(restaurantProductId);
        Product product = restaurantProduct.getBatch().getSupplyOrderProduct().getProduct();
        product.setRestaurantProductId(restaurantProductId);
        productRepository.save(product);
        return restaurantProduct.getBatch().getSupplyOrderProduct();
    }

}
