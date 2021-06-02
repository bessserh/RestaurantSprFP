package restaurantSF.finalproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "ordered_dishes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(OrderedDishesPK.class)
public class OrderedDishes {

    @Id
    private Integer order_id;
    @Id
    private Integer dish_id;

    @Column(name = "amount")
    private Integer amount = 1;

    @Column(name = "total_dish")
    private Double totalDish;

    public void increaseAmount() {
        this.amount++;
    }

    public void decreaseAmount() {
        this.amount--;
    }
}
