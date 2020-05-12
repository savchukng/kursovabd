package dinoco.kursova.bd.controller;

import dinoco.kursova.bd.model.*;
import dinoco.kursova.bd.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/supply-orders")
public class SupplyOrderController {

    private final SupplyOrderService supplyOrderService;
    private final SupplyOrderProductService supplyOrderProductService;
    private final RestaurantService restaurantService;
    private final SupplierService supplierService;
    private final SupplyService supplyService;

    @PostMapping("/add")
    public String addSupplyOrder(SupplyOrder supplyOrder, Principal principal) {
        Integer id = supplyOrderService.save(supplyOrder, Integer.parseInt(principal.getName()));
        return "redirect:/supply-orders/" + id + "/add-products";
    }

    @GetMapping
    public String getAllSupplyOrders(Model model, HttpServletRequest request) {
        List<SupplyOrder> supplyOrders = supplyOrderService.getAll();
        supplyOrders.forEach(supplyOrder -> {
            supplyOrder.setSupplyOrderProducts(supplyOrderProductService.getBySupplyOrder(supplyOrder));
            supplyOrder.setOrderPrice(supplyOrder.getSupplyOrderProducts().stream()
                    .mapToInt(supplyOrderProduct -> supplyOrderProduct.getAmount() * supplyOrderProduct.getPrice())
                    .sum());
            supplyOrder.setSupply(supplyService.getBySupplyOrder(supplyOrder));
        });
        model.addAttribute("supplyOrders", supplyOrderService.getAll());
        model.addAttribute("isNotWaiter", !request.isUserInRole("ROLE_WAITER"));

        return "supply-orders";
    }

    @GetMapping("/add")
    public String addSupplyOrder(Model model) {
        model.addAttribute("supplyOrder", new SupplyOrder());
        model.addAttribute("restaurants", restaurantService.getAll());
        model.addAttribute("suppliers", supplierService.getAll());
        return "add-supply-order";
    }

    @GetMapping("/{id}/add-products")
    public String addSupplyOrderProduct(@PathVariable Integer id, Model model) {
        SupplyOrder supplyOrder = supplyOrderService.get(id);
        supplyOrder.setSupplyOrderProducts(supplyOrderProductService.getBySupplyOrder(supplyOrder));
        List<SupplyOrderProduct> supplyOrderProducts = supplyOrder.getSupplyOrderProducts();
        model.addAttribute("supplyOrderProducts", supplyOrderProducts);
        model.addAttribute("orderPrice", supplyOrderProducts.stream()
                .mapToInt(supplyOrderProduct -> supplyOrderProduct.getAmount() * supplyOrderProduct.getPrice())
                .sum());
        model.addAttribute("newSupplyOrderProduct", SupplyOrderProduct.builder().supplyOrder(supplyOrder).build());
        List<Product> usedProducts = supplyOrderProducts.stream()
                .map(SupplyOrderProduct::getProduct)
                .collect(Collectors.toList());
        List<SupplierProduct> supplierProducts = supplierService.getProducts(supplyOrder.getSupplier());
        List<SupplierProduct> availableProducts = supplierProducts.stream()
                .filter(supplierProduct -> !usedProducts.contains(supplierProduct.getProduct()))
                .collect(Collectors.toList());
        model.addAttribute("availableProducts", availableProducts);
        return "add-supply-order-products";
    }

    @PostMapping("/{supplyOrderId}/add-products")
    public String updateProducts(@RequestParam("id") Integer id, @RequestParam("amount") Integer amount,
                                 @PathVariable Integer supplyOrderId) {
        supplyOrderService.updateProduct(id, amount);
        return "redirect:/supply-orders/" + supplyOrderId + "/add-products";
    }

    @PostMapping("/add-product")
    public String addProducts(SupplyOrderProduct supplyOrderProduct, @RequestParam("supplierProductId") Integer supplierProductId) {
        supplyOrderService.saveProduct(supplyOrderProduct, supplierProductId);
        return "redirect:/supply-orders/" + supplyOrderProduct.getSupplyOrder().getId() + "/add-products";
    }

    @GetMapping("/remove-product/{id}")
    public String removeProduct(@PathVariable Integer id) {
        Integer supplyOrderId = supplyOrderProductService.delete(id);
        return "redirect:/supply-orders/" + supplyOrderId + "/add-products";
    }

    @GetMapping("/{id}/delivered")
    public String delivered(@PathVariable Integer id, Principal principal) {
        Integer supplyId = supplyService.add(id, Integer.parseInt(principal.getName()));
        return "redirect:/supplies/" + supplyId + "/batches";
    }

}
