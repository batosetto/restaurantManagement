package ca.humber.barbara_tosetto.finalproject;

import ca.humber.barbara_tosetto.finalproject.models.Dish;
import ca.humber.barbara_tosetto.finalproject.services.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FinalProjectApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(FinalProjectApplication.class, args);
    }

    private final DishService dishService;

    @Autowired
    FinalProjectApplication(DishService dishService) { this.dishService = dishService; };

    @Override
    public void run(String... args) throws Exception {

        dishService.addDish(new Dish(1, "Bruschetta", "Veg", 5.0));
        dishService.addDish(new Dish(2, "Margherita Pizza", "Veg", 17.90));
        dishService.addDish(new Dish(3, "Pasta Carbonara", "Non-veg", 18.50));
        dishService.addDish(new Dish(4, "Lasagna", "Veg", 119.0));
        dishService.addDish(new Dish(5, "Chicken Parmigiana", "Non-veg", 20.0));
        dishService.addDish(new Dish(6, "Caprese Salad", "Veg", 12.0));
        dishService.addDish(new Dish(7, "Polenta", "Vegan", 10.0));
        dishService.addDish(new Dish(8, "Risotto", "Veg", 19.0));
        dishService.addDish(new Dish(9, "Antipasto", "Non-veg", 12.0));
        dishService.addDish(new Dish(10, "Tiramisu", "Veg", 9.0));
        dishService.addDish(new Dish(11, "Pepperoni Pizza", "Non-veg", 18.0));
        dishService.addDish(new Dish(12, "Insalata Mista", "Veg", 10.0));
        dishService.addDish(new Dish(13, "Osso Buco", "Non-veg", 25.0));
        dishService.addDish(new Dish(14, "Cannoli", "Veg", 8.0));
        dishService.addDish(new Dish(15, "Fettuccine Alfredo", "Veg", 18.0));
        dishService.addDish(new Dish(16, "Vitello Tonnato", "Non-veg", 20.0));
        dishService.addDish(new Dish(17, "Arancini", "Veg", 12.0));
        dishService.addDish(new Dish(18, "Saltimbocca", "Non-veg", 22.0));
        dishService.addDish(new Dish(19, "Minestrone", "Vegan", 9.0));
        dishService.addDish(new Dish(20, "Gnocchi", "Veg", 16.0));
        dishService.addDish(new Dish(21, "Bistecca Fiorentina", "Non-veg", 30.0));
        dishService.addDish(new Dish(22, "Panna Cotta", "Veg", 10.0));


    }

}
