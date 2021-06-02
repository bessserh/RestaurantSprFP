package restaurantSF.finalproject.Service;

import restaurantSF.finalproject.entity.OrderedDishes;

import java.util.List;

public interface OrderedDishesService {

    List<OrderedDishes> findOrderDishesByOrder_id(Integer orderId);

    OrderedDishes findOrderDishesByOrderAndDishId(Integer orderId, Integer dishId);

    List<OrderedDishes> findAllDishes();

    OrderedDishes saveOrderDish(OrderedDishes ordersDishes);

    void deleteOrderDish(OrderedDishes ordersDishes);

    void changeAmount(Integer orderId, Integer dishId, String type);

    OrderedDishes addOrderDishToOrder(Integer orderId, Integer dishId);

}
