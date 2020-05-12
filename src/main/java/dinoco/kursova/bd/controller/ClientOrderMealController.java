package dinoco.kursova.bd.controller;

import dinoco.kursova.bd.service.ClientOrderMealService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/client-order-meals")
@RequiredArgsConstructor
public class ClientOrderMealController {

    private final ClientOrderMealService clientOrderMealService;

    @GetMapping("/{id}/cook")
    public String startCooking(@PathVariable Integer id) {
        Integer clientOrderId = clientOrderMealService.startCooking(id);
        return "redirect:/client-orders/" + clientOrderId + "/meals";
    }

    @GetMapping("/{id}/ready")
    public String ready(@PathVariable Integer id) {
        Integer clientOrderId = clientOrderMealService.ready(id);
        return "redirect:/client-orders/" + clientOrderId + "/meals";
    }

}
