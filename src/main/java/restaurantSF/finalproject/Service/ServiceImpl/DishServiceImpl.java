package restaurantSF.finalproject.Service.ServiceImpl;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import restaurantSF.finalproject.DTO.DishDTO;
import restaurantSF.finalproject.Service.DishService;
import restaurantSF.finalproject.converters.DishConverter;
import restaurantSF.finalproject.entity.Dishes;
import restaurantSF.finalproject.entity.Enums.Category;
import restaurantSF.finalproject.errorValid.ValidationException;
import restaurantSF.finalproject.repository.DishRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class DishServiceImpl implements DishService {

    private final DishRepository dishRepository;
    private final DishConverter dishConverter;

    @Override
    public DishDTO saveDish(DishDTO dishDTO) throws ValidationException {
        Dishes savedDish = dishRepository.save(dishConverter.fromDishDtoToDish(dishDTO));
        return dishConverter.fromDishToDishDto(savedDish);
    }

    @Override
    public void deleteDish(Integer dishId) {
        dishRepository.deleteById(dishId);
    }

    @Override
    public DishDTO findByName(String name) {
        return dishConverter.fromDishToDishDto(dishRepository.findByCategory(name));
    }

    @Override
    public Dishes findDishById(Integer id) {
        return dishRepository.findById(id).orElse(null);
    }

    @Override
    public List<DishDTO> findAll() {
        return dishRepository.findAll()
                .stream()
                .map(dishConverter::fromDishToDishDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<Dishes> findPaginated(int pageNum, int pageSize, String sortField, String sortDirection) {

        return dishRepository.findAll(PageRequest.of(pageNum-1, pageSize,
                sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                        Sort.by(sortField).ascending() :
                        Sort.by(sortField).descending()));
    }

    @Override
    public Page<Dishes> paginatedCategory(Category category, int pageNum, int pageSize,
                                          String sortField, String sortDirection) {

        return dishRepository.findAllByCategory(category,
                PageRequest.of(pageNum-1, pageSize,
                sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                        Sort.by(sortField).ascending() :
                        Sort.by(sortField).descending()));

    }

}

