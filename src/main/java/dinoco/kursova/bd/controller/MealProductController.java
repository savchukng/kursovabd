package dinoco.kursova.bd.controller;

import dinoco.kursova.bd.model.Meal;
import dinoco.kursova.bd.model.MealProduct;
import dinoco.kursova.bd.model.Product;
import dinoco.kursova.bd.service.MealProductService;
import dinoco.kursova.bd.service.MealService;
import dinoco.kursova.bd.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class MealProductController {

    private final MealService mealService;
    private final ProductService productService;
    private final MealProductService mealProductService;

    @GetMapping("/meals/{id}/update-products")
    public String updateMealProducts(@PathVariable Integer id, Model model) {
        List<MealProduct> mealProducts = mealService.get(id).getMealProducts();
        List<Product> products = mealProducts.stream()
                .map(MealProduct::getProduct)
                .collect(Collectors.toList());
        List<Product> availableProducts = productService.getAll();
        availableProducts.removeAll(products);
        model.addAttribute("mealProducts", mealProducts);
        model.addAttribute("id", id);
        model.addAttribute("availableProducts", availableProducts);
        model.addAttribute("newMealProduct", MealProduct.builder().meal(mealService.get(id)).build());
        return "update-meal-products";
    }

    @PostMapping("/meals/{mealId}/update-products")
    public String updateMealProducts(@RequestParam("id") Integer id, @RequestParam("amount") Integer amount, @PathVariable Integer mealId, Model model) {
        mealProductService.update(id, amount);
        model.addAttribute("mealProducts", mealService.get(mealId).getMealProducts());
        model.addAttribute("id", mealId);
        return "redirect:/meals/" + mealId + "/update-products";
    }

    @PostMapping("/meals/add-product")
    public String addMealProduct(MealProduct mealProduct) {
        mealProductService.save(mealProduct);
        return "redirect:/meals/" + mealProduct.getMeal().getId() + "/update-products";
    }

    @GetMapping("/meals/{mealId}/delete-product/{id}")
    public String deleteMealProduct(@PathVariable Integer mealId, @PathVariable Integer id, Model model) {
        mealProductService.delete(id);
        model.addAttribute("mealProducts", mealService.get(mealId).getMealProducts());
        model.addAttribute("id", mealId);
        return "redirect:/meals/" + mealId + "/update-products";
    }

}
