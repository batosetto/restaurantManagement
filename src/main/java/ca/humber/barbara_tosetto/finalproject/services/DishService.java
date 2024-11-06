package ca.humber.barbara_tosetto.finalproject.services;

import ca.humber.barbara_tosetto.finalproject.models.Dish;
import ca.humber.barbara_tosetto.finalproject.repositores.DishRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;

@Service
public class DishService {
    private final DishRepository dishRepo;

    @Autowired
    public DishService(DishRepository dishRepo){
        this.dishRepo = dishRepo;
    }

    //Get all dishes
    public List<Dish> getAllDishes(){
        return dishRepo.findAll();
    }

    //Get filtered dishes
    public List<Dish> getFilteredDishes(String searchedCategory, Double searchedPrice){
        return dishRepo.findByCategoryAndPrice(searchedCategory, searchedPrice);
    }

    //Get paginated dishes
    public Page<Dish> getPaginatedDishes(int pageNo, int pageSize, String sortField, String sortDirection){
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo-1, pageSize, sort);
        return dishRepo.findAll(pageable);
    }

    //Add dish
    public int addDish(Dish dish){
        if(dish.getPrice()>8){
            dishRepo.save(dish);
            return 1;
        }
        return 0;
    }

    //Remove dish
    public int deleteDish(int id){
        if(dishRepo.existsById(id)){
            dishRepo.deleteById(id);
            return 1;
        }
        return 0;
    }

    //Update dish
    public void updatedDish(Dish dish){
        dishRepo.save(dish);
    }

    //Get dish by id
    public Optional<Dish> getDishById(int id){
        return dishRepo.findById(id);
    }
}
