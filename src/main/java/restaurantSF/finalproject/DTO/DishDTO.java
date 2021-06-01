package restaurantSF.finalproject.DTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import restaurantSF.finalproject.entity.Enums.Category;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DishDTO {

    private Integer id;
    private String name;
    private Double price;

    private String image;
    private String description;

    private Category category;
}
