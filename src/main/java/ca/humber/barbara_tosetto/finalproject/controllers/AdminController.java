package ca.humber.barbara_tosetto.finalproject.controllers;

import ca.humber.barbara_tosetto.finalproject.models.Dish;
import ca.humber.barbara_tosetto.finalproject.services.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/restaurant/admin")
public class AdminController {
    private final DishService dishService;

    @Autowired
    public AdminController(DishService dishService) { this.dishService = dishService; };
    @Value("${restaurant.name}")
    private String name;

    @GetMapping("/add-dish")
    public String addDish(Model model){
        model.addAttribute("dish", new Dish());
        return "/admin/add-dish";
    }
    @PostMapping("/add-dish")
    public String addDish(@ModelAttribute Dish dish, Model model){
        int status = dishService.addDish(dish);
        if (status == 0){
            model.addAttribute("error", "Price must not exceed $20");
            return "/admin/add-dish";
        }
        return "redirect:/restaurant/menu/1?message=Dish added successfully";
    }
    @GetMapping("/delete/{id}")
    public String deleteDish(@PathVariable int id){
        int status = dishService.deleteDish(id);
        if (status > 0){
            return "redirect:/restaurant/menu/1?message=Dish deleted successfully";
        }
        return "redirect:/restaurant/menu/1?message=Dish does not exist";
    }
    @GetMapping("/update/{id}")
    public String updateDish(Model model, @PathVariable int id){
        Optional<Dish> dishToUpdate = dishService.getDishById(id);
        model.addAttribute("dish", dishToUpdate.orElse(null));
        return "/admin/add-dish";
    }
    @PostMapping("/update-dish")
    public String updateDish(@ModelAttribute Dish dish){
        dishService.updatedDish(dish);
        return "redirect:/restaurant/menu/1?message=Dish updated";
    }
}
