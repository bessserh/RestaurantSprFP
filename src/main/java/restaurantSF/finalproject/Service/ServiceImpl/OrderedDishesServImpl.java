package restaurantSF.finalproject.Service.ServiceImpl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import restaurantSF.finalproject.Service.OrderedDishesService;
import restaurantSF.finalproject.entity.OrderedDishes;
import restaurantSF.finalproject.repository.OrderedDishesRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderedDishesServImpl implements OrderedDishesService {

    private final OrderedDishesRepository orderedDishesRepository;

    @Override
    public List<OrderedDishes> findOrderDishesByOrder_id(Integer orderId) {
        List<OrderedDishes> ordersDishesAll = orderedDishesRepository.findAll();
        List<OrderedDishes> orderedDishesList = new ArrayList<>();
        for (OrderedDishes orderDish : ordersDishesAll) {
            if (orderDish.getOrder_id().equals(orderId)) {
                orderedDishesList.add(orderDish);
            }
        }
        return orderedDishesList;
    }

    @Override
    public OrderedDishes findOrderDishesByOrderAndDishId(Integer orderId, Integer dishId) {
        List<OrderedDishes> ordersDishesListByOrder = findOrderDishesByOrder_id(orderId);
        if(ordersDishesListByOrder!=null) {
            for (OrderedDishes orderDish : ordersDishesListByOrder) {
                if (orderDish.getDish_id().equals(dishId)) return orderDish;
            }
        }
        return null;
    }

    @Override
    public List<OrderedDishes> findAllDishes() {
        return orderedDishesRepository.findAll();
    }

    @Override
    public OrderedDishes saveOrderDish(OrderedDishes ordersDishes) {
        return orderedDishesRepository.save(ordersDishes);
    }

    @Override
    public void deleteOrderDish(OrderedDishes ordersDishes) {
        orderedDishesRepository.delete(ordersDishes);
    }

    @Override
    @Transactional
    public void changeAmount(Integer orderId, Integer dishId, String type) {
        OrderedDishes orderDishesFromExist = findOrderDishesByOrderAndDishId(orderId, dishId);
        if (type.equals("incItem")) {
            orderDishesFromExist.increaseAmount();
        } else {
            if (orderDishesFromExist.getAmount() > 1) {
                orderDishesFromExist.decreaseAmount();
            }
        }
        saveOrderDish(orderDishesFromExist);
    }

    @Override
    @Transactional
    public OrderedDishes addOrderDishToOrder(Integer orderId, Integer dishId) {
        OrderedDishes orderDish = new OrderedDishes(orderId, dishId, 1, 0d);
        OrderedDishes orderDishesFromExist = findOrderDishesByOrderAndDishId(orderId, dishId);

        if (orderDishesFromExist != null) {
            orderDish = orderDishesFromExist;
            orderDish.increaseAmount();
        }
        return saveOrderDish(orderDish);
    }
}

