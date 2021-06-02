package restaurantSF.finalproject.Service;

import org.springframework.data.domain.Page;
import restaurantSF.finalproject.DTO.DishDTO;
import restaurantSF.finalproject.entity.Dishes;
import restaurantSF.finalproject.errorValid.ValidationException;

import java.util.List;

public interface DishService {

    DishDTO saveDish(DishDTO dishDTO) throws ValidationException;
    void deleteDish(Integer dishId);
    DishDTO findByName(String name);
    List<DishDTO> findAll();
    Dishes findDishById(Integer id);
    Page<Dishes> findPaginated(int pageNum, int pageSize, String sortField, String sortDirection);
}
