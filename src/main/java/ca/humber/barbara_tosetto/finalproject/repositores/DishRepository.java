package ca.humber.barbara_tosetto.finalproject.repositores;

import ca.humber.barbara_tosetto.finalproject.models.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DishRepository extends JpaRepository<Dish, Integer> {
    @Query(value = "SELECT * from Dish where  lower(category) = lower(?1) AND price = ?2", nativeQuery = true)
    List<Dish> findByCategoryAndPrice(String searchedCategory, Double searchedPrice);
}
