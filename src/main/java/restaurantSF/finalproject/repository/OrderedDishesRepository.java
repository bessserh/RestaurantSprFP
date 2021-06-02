package restaurantSF.finalproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import restaurantSF.finalproject.entity.OrderedDishes;
import restaurantSF.finalproject.entity.OrderedDishesPK;

public interface OrderedDishesRepository extends JpaRepository<OrderedDishes, OrderedDishesPK> {

}
