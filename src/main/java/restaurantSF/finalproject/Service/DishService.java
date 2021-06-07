package restaurantSF.finalproject.Service;

import org.springframework.data.domain.Page;
import restaurantSF.finalproject.DTO.DishDTO;
import restaurantSF.finalproject.entity.Dishes;
import restaurantSF.finalproject.entity.Enums.Category;
import restaurantSF.finalproject.errorValid.ValidationException;

import java.util.List;
import java.util.Optional;

public interface DishService {

    DishDTO saveDish(DishDTO dishDTO) throws ValidationException;
    void deleteDish(Integer dishId);
    DishDTO findByName(String name);
    List<DishDTO> findAll();
    Dishes findDishById(Integer id);
    Page<Dishes> findPaginated(int pageNum, int pageSize, String sortField, String sortDirection);

    Page<Dishes> paginatedCategory(Category category, int pageNum, int pageSize, String sortField,
                                   String sortDirection);
}
