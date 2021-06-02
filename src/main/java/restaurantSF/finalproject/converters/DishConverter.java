package restaurantSF.finalproject.converters;

import org.springframework.stereotype.Component;
import restaurantSF.finalproject.DTO.DishDTO;
import restaurantSF.finalproject.entity.Dishes;

@Component
public class DishConverter {

    public Dishes fromDishDtoToDish(DishDTO dishDTO) {
        Dishes dishes = new Dishes();
        dishes.setId(dishDTO.getId());
        dishes.setName(dishDTO.getName());
        dishes.setPrice(dishDTO.getPrice());
        dishes.setImage(dishDTO.getImage());
        dishes.setDescription(dishDTO.getDescription());
        dishes.setCategory(dishDTO.getCategory());
        return dishes;
    }

    public DishDTO fromDishToDishDto(Dishes dish) {
        return DishDTO.builder()
                .id(dish.getId())
                .name(dish.getName())
                .price(dish.getPrice())
                .image(dish.getImage())
                .description(dish.getDescription())
                .category(dish.getCategory())
                .build();
    }
}