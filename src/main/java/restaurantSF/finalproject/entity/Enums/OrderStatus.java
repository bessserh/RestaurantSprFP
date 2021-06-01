package restaurantSF.finalproject.entity.Enums;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public enum OrderStatus{
    MAKING(0),
    APPROVING(1),
    COOKING(2),
    DELIVERING(3),
    CLOSED(4),
    CANCELED(5);

    private Integer id;

    public Integer getId() {
        return id;
    }

    public static OrderStatus findStatusById(Integer id){
        for(OrderStatus s : OrderStatus.values()){
            if(s.getId()==id) return s;
        }
        return null;
    }

    @Override
    public String toString() {
        return this.name();
    }
}