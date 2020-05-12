package dinoco.kursova.bd.controller;

import dinoco.kursova.bd.model.Product;
import dinoco.kursova.bd.service.AmountTypeService;
import dinoco.kursova.bd.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final AmountTypeService amountTypeService;

    @GetMapping
    public String getAllProducts(Model model, @RequestParam(required = false, defaultValue = "") String search, HttpServletRequest request) {
        model.addAttribute("products", productService.searchProducts(search));
        model.addAttribute("previousSearch", search);
        model.addAttribute("isAdmin", request.isUserInRole("ROLE_ADMIN"));
        return "products";
    }

    @GetMapping("/add")
    public String addProduct(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("amountTypes", amountTypeService.getAll());
        return "add-product";
    }

    @GetMapping("/update/{id}")
    public String updateProduct(@PathVariable Integer id, Model model) {
        model.addAttribute("product", productService.get(id));
        model.addAttribute("amountTypes", amountTypeService.getAll());
        return "update-product";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Integer id, Model model) {
        productService.delete(id);
        model.addAttribute("products", productService.getAll());
        return "redirect:/products";
    }

    @PostMapping("/update")
    public String updateProduct(Product product, Model model) {
        productService.save(product);
        model.addAttribute("products", productService.getAll());
        return "redirect:/products";
    }

    @PostMapping("/add")
    public String addProduct(Product product, Model model) {
        productService.save(product);
        model.addAttribute("products", productService.getAll());
        return "redirect:/products";
    }
}
