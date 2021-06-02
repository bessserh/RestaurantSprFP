package restaurantSF.finalproject.Service;

import restaurantSF.finalproject.DTO.OrderDTO;
import restaurantSF.finalproject.entity.Orders;
import restaurantSF.finalproject.entity.Users;

import java.util.List;

public interface OrderService {

    Orders saveOrder(Orders order);

    void deleteOrder(Integer dishId);

    Orders findById(Integer orderId);

    Orders findOrdersByUserId(Integer userId);

    List<OrderDTO> findAll();

    void nextStatus(String type, Orders order);

    Orders createNewOrder(Users user);
}
