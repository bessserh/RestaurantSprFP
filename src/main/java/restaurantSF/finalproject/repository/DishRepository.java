package restaurantSF.finalproject.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import restaurantSF.finalproject.entity.Dishes;

import java.util.List;
import java.util.Optional;

public interface DishRepository extends JpaRepository<Dishes, Integer> {

    @Override
    Optional<Dishes> findById(Integer integer);

    Dishes findByCategory(String category);
}
