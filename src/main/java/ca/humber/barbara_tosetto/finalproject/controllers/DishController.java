package ca.humber.barbara_tosetto.finalproject.controllers;

import ca.humber.barbara_tosetto.finalproject.models.Dish;
import ca.humber.barbara_tosetto.finalproject.services.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/restaurant")
public class DishController {
    private final DishService dishService;

    @Autowired
    public DishController(DishService dishService) { this.dishService = dishService; };

    @Value("${restaurant.name}")
    private String name;

    @Value("${page.size}")
    private int pageSize;

    @GetMapping("/home")
    public String home(Model model){
        model.addAttribute("name", name);
        return "home";
    };
    @GetMapping("/menu/{pageNo}")
    public String getAllDishes(Model model,
                               @RequestParam(required = false) String message,
                               @RequestParam(required = false) String searchedCategory,
                               @RequestParam(required = false) Double searchedPrice,
                               @PathVariable int pageNo,
                               @RequestParam(required = false) String sortField,
                               @RequestParam(required = false) String sortDirection){
        if(sortField == null) sortField = "id";
        if(sortDirection == null) sortDirection = "asc";

        Page<Dish> page = dishService.getPaginatedDishes(pageNo, pageSize, sortField, sortDirection);

        if(searchedCategory!=null && searchedPrice!=null){
            List<Dish> filteredDishes = dishService.getFilteredDishes(searchedCategory, searchedPrice);
            model.addAttribute("dishes", filteredDishes.isEmpty() ? page.getContent() : filteredDishes);
            model.addAttribute("message", filteredDishes.isEmpty() ? "Not filtered" : "Filtered successfully");
            return "menu";
        }
        model.addAttribute("dishes", page.getContent());
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDirection", sortDirection);
        model.addAttribute("reverseSortDirection", sortDirection.equals("asc") ? "desc" : "asc");
        model.addAttribute("message", message);
        return "menu";
    }
}
