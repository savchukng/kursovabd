package dinoco.kursova.bd.controller;

import dinoco.kursova.bd.model.Product;
import dinoco.kursova.bd.model.RestaurantProduct;
import dinoco.kursova.bd.service.ProductDiscardService;
import dinoco.kursova.bd.service.ProductService;
import dinoco.kursova.bd.service.RestaurantProductService;
import dinoco.kursova.bd.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final RestaurantProductService restaurantProductService;
    private final ProductDiscardService productDiscardService;
    private final ProductService productService;

    @GetMapping
    public String getRestaurants(Model model) {
        model.addAttribute("restaurants", restaurantService.getAll());
        return "restaurants";
    }

    @GetMapping("/{id}/products")
    public String getProducts(Model model, @PathVariable Integer id) {
        List<RestaurantProduct> restaurantProducts = restaurantProductService.getByRestaurantId(id);
        Map<Product, Integer> productsAmounts = new HashMap<>();
        restaurantProducts.forEach(restaurantProduct -> {
            Product product = restaurantProduct.getBatch().getSupplyOrderProduct().getProduct();
            productsAmounts.put(product, productsAmounts.getOrDefault(product, 0) + restaurantProduct.getAmount());
        });
        model.addAttribute("productsAmounts", productsAmounts);
        model.addAttribute("restaurantId", id);
        return "restaurant-products";
    }

    @GetMapping("/{restaurantId}/products/{productId}/batches")
    public String getBatches(Model model, @PathVariable Integer restaurantId, @PathVariable Integer productId) {
        model.addAttribute("restaurantProducts", restaurantProductService.getByRestaurantIdAndProductId(restaurantId, productId));
        model.addAttribute("productName", productService.get(productId).getName());
        return "restaurant-batches";
    }

    @GetMapping("/{id}/discards")
    public String getDiscards(Model model, @PathVariable Integer id) {
        model.addAttribute("productDiscards", productDiscardService.getByRestaurantId(id));
        return "restaurant-discards";
    }

}
