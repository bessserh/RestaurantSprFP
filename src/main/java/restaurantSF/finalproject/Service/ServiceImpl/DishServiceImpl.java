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
import restaurantSF.finalproject.errorValid.ValidationException;
import restaurantSF.finalproject.repository.DishRepository;

import java.util.List;
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
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();
        Pageable page = PageRequest.of(pageNum-1, pageSize, sort);
        return dishRepository.findAll(page);
    }
    //todo optional list dishes
    @Override
    public List<Dishes> paginatedCategory(Page<Dishes> dishesPage, String category) {
        List<Dishes> dishesCat = dishesPage.getContent()
                .stream().filter(dishes1 -> dishes1.getCategory().toString()
                .equalsIgnoreCase(category)).collect(Collectors.toList());

        return dishesCat.isEmpty() ? dishesPage.getContent() : dishesCat;
    }

}

