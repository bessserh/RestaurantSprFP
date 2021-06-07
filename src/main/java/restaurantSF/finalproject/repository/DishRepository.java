package restaurantSF.finalproject.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import restaurantSF.finalproject.entity.Dishes;
import restaurantSF.finalproject.entity.Enums.Category;

import java.util.List;
import java.util.Optional;

public interface DishRepository extends JpaRepository<Dishes, Integer> {

    @Override
    Optional<Dishes> findById(Integer integer);

    Dishes findByCategory(String category);

    Page<Dishes> findAllByCategory(Category category, Pageable page);


}
