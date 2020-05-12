package dinoco.kursova.bd.controller;

import dinoco.kursova.bd.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/suppliers")
public class SupplierController {

    private final SupplierService supplierService;

    @GetMapping
    public String getAllSuppliers(Model model) {
        model.addAttribute("suppliers", supplierService.getAll());
        return "suppliers";
    }

    @GetMapping("/{id}/products")
    public String getSupplierProducts(@PathVariable Integer id, Model model) {
        model.addAttribute("supplierProducts", supplierService.getProducts(supplierService.get(id)));
        model.addAttribute("supplier", supplierService.get(id));
        return "supplier-products";
    }

}
