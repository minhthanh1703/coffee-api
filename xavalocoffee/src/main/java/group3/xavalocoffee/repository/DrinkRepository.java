package group3.xavalocoffee.repository;

import group3.xavalocoffee.entities.Drink;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DrinkRepository extends JpaRepository<Drink, Integer> {
    List<Drink> findAllByDisable(boolean disable);

    List<Drink> findByCategoryIdEquals(int categoryId);

    Float findPriceById(int drinkId);
}
