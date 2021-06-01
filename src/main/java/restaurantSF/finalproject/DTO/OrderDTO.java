package restaurantSF.finalproject.DTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import restaurantSF.finalproject.entity.Dishes;
import restaurantSF.finalproject.entity.Enums.OrderStatus;
import restaurantSF.finalproject.entity.Users;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    private Integer id;

    private OrderStatus status;
    private Users user;

    private LocalDateTime creationDate;
    private LocalDateTime updateDate;

    private List<Dishes> dishes;

}