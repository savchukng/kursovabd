package dinoco.kursova.bd.controller;

import dinoco.kursova.bd.model.ProductDiscard;
import dinoco.kursova.bd.model.RestaurantProduct;
import dinoco.kursova.bd.model.SupplyOrderProduct;
import dinoco.kursova.bd.service.DiscardReasonService;
import dinoco.kursova.bd.service.ProductDiscardService;
import dinoco.kursova.bd.service.ProductService;
import dinoco.kursova.bd.service.RestaurantProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/restaurant-products")
public class RestaurantProductController {

    private final DiscardReasonService discardReasonService;
    private final RestaurantProductService restaurantProductService;
    private final ProductService productService;
    private final ProductDiscardService productDiscardService;

    @GetMapping("/{id}/discard")
    public String discard(@PathVariable Integer id, Model model) {
        model.addAttribute("discardReasons", discardReasonService.getAll());
        model.addAttribute("productDiscard", ProductDiscard.builder().restaurantProduct(restaurantProductService.get(id)).build());
        return "add-discard-product";
    }

    @PostMapping("/discard")
    public String discard(ProductDiscard productDiscard, Model model) {
        productDiscardService.add(productDiscard);
        return "redirect:/restaurants/1/products/2/batches";
    }

    @GetMapping("/{id}/make-active")
    public String makeActive(@PathVariable Integer id, Model model) {
        SupplyOrderProduct supplyOrderProduct = productService.makeActive(id);
        return "redirect:/restaurants/" + supplyOrderProduct.getSupplyOrder().getRestaurant().getId() + "/products/" +
                supplyOrderProduct.getProduct().getId() + "/batches";
    }

}
