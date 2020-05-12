package dinoco.kursova.bd.controller;

import dinoco.kursova.bd.model.ClientOrder;
import dinoco.kursova.bd.model.ClientOrderMeal;
import dinoco.kursova.bd.model.Meal;
import dinoco.kursova.bd.service.ClientOrderMealService;
import dinoco.kursova.bd.service.ClientOrderService;
import dinoco.kursova.bd.service.MealService;
import dinoco.kursova.bd.service.TableService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/client-orders")
public class ClientOrderController {

    private final ClientOrderService clientOrderService;
    private final ClientOrderMealService clientOrderMealService;
    private final MealService mealService;
    private final TableService tableService;

    @GetMapping
    public String getOrders(Model model, @RequestParam String paid) {
        model.addAttribute("clientOrders", clientOrderService.getClientOrders(paid));
        return "client-orders";
    }

    @GetMapping("/{id}/pay")
    public String pay(@PathVariable Integer id) {
        clientOrderService.pay(id);
        return "redirect:/client-orders?paid=true";
    }

    @GetMapping("/add")
    public String addOrder(Model model) {
        model.addAttribute("tables", tableService.getByRestaurantId(1));
        model.addAttribute("newClientOrder", new ClientOrder());
        return "add-client-order";
    }

    @PostMapping("/add")
    public String addOrder(ClientOrder clientOrder, Principal principal) {
        Integer id = clientOrderService.add(clientOrder, Integer.parseInt(principal.getName()));
        return "redirect:/client-orders/" + id + "/meals";
    }

    @GetMapping("/{id}/meals")
    public String addMeals(Model model, @PathVariable Integer id) {
        List<ClientOrderMeal> clientOrderMeals = clientOrderMealService.getByClientOrderId(id);
        List<Meal> meals = mealService.getAll();
        ClientOrder clientOrder = clientOrderService.get(id);
        model.addAttribute("clientOrderMeals", clientOrderMeals);
        model.addAttribute("meals", meals);
        model.addAttribute("clientOrder", clientOrder);
        model.addAttribute("newClientOrderMeal", ClientOrderMeal.builder().clientOrder(clientOrder).build());
        return "client-order-meals";
    }

    @PostMapping("/add-meal")
    public String addMeal(ClientOrderMeal clientOrderMeal) {
        clientOrderMealService.add(clientOrderMeal);
        return "redirect:/client-orders/" + clientOrderMeal.getClientOrder().getId() + "/meals";
    }

    @GetMapping("/{clientOrderId}/delete-meal/{mealId}")
    public String deleteMeal(@PathVariable Integer mealId, @PathVariable Integer clientOrderId) {
        clientOrderMealService.delete(mealId);
        return "redirect:/client-orders/" + clientOrderId + "/meals";
    }
}
