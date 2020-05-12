package dinoco.kursova.bd.controller;

import dinoco.kursova.bd.model.Meal;
import dinoco.kursova.bd.service.AmountTypeService;
import dinoco.kursova.bd.service.MealGroupService;
import dinoco.kursova.bd.service.MealService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/meals")
@RequiredArgsConstructor
public class MealController {

    private final MealService mealService;
    private final MealGroupService mealGroupService;
    private final AmountTypeService amountTypeService;

    @GetMapping
    public String getAllMeals(Model model, @RequestParam(required = false, defaultValue = "") String search, HttpServletRequest request) {
        model.addAttribute("meals", mealService.searchMeals(search));
        model.addAttribute("previousSearch", search);
        model.addAttribute("isAdmin", request.isUserInRole("ROLE_ADMIN"));
        return "meals";
    }

    @GetMapping("/update/{id}")
    public String updateMeal(@PathVariable Integer id, Model model) {
        model.addAttribute("meal", mealService.get(id));
        model.addAttribute("groups", mealGroupService.getAll());
        model.addAttribute("amountTypes",amountTypeService.getAll());
        return "update-meal";
    }

    @GetMapping("/add")
    public String addMeal(Model model) {
        model.addAttribute("meal", new Meal());
        model.addAttribute("groups", mealGroupService.getAll());
        model.addAttribute("amountTypes",amountTypeService.getAll());
        return "add-meal";
    }

    @PostMapping("/add")
    public String addMeal(Meal meal) {
        Integer id = mealService.save(meal);
        return "redirect:/meals/" + id + "/update-products";
    }

    @PostMapping("/update")
    public String updateMeal(Meal meal, Model model) {
        mealService.save(meal);
        model.addAttribute("meals", mealService.getAll());
        return "meals";
    }

    @GetMapping("/{id}/delete")
    public String deleteMeal(@PathVariable Integer id) {
        mealService.delete(id);
        return "redirect:/meals";
    }

}
