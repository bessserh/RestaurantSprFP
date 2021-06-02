package restaurantSF.finalproject.Service.ServiceImpl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import restaurantSF.finalproject.DTO.OrderDTO;
import restaurantSF.finalproject.Service.OrderService;
import restaurantSF.finalproject.converters.OrderConverter;
import restaurantSF.finalproject.entity.Enums.OrderStatus;
import restaurantSF.finalproject.entity.Orders;
import restaurantSF.finalproject.entity.Users;
import restaurantSF.finalproject.repository.OrderRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderConverter ordersConverter;

    @Override
    public Orders saveOrder(Orders order){
        return orderRepository.save(order);
    }

    @Override
    public void deleteOrder(Integer orderId) {
        orderRepository.deleteById(orderId);
    }

    @Override
    public Orders findById(Integer orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }


    @Override
    public Orders findOrdersByUserId(Integer userId){
        List<Orders> orders = orderRepository.findByUserId(userId);
        for (Orders order : orders) {
            if(!order.getStatus().equals(OrderStatus.CANCELED) &&
                    !order.getStatus().equals(OrderStatus.CLOSED)){
                return order;
            }
        }
        return null;
    }


    @Override
    public List<OrderDTO> findAll() {
        return orderRepository.findAll()
                .stream()
                .map(ordersConverter::fromOrderToOrderDto)
                .collect(Collectors.toList());
    }

    @Override
    public void nextStatus(String type, Orders order) {
        if(type.equals("cancel")){
            order.setStatus(OrderStatus.CANCELED);
        }
        else{
            Integer currentStatusId = order.getStatus().getId();
            Integer nextStatus = currentStatusId+1;
            if(nextStatus<5){
                order.setStatus(OrderStatus.findStatusById(nextStatus));
            }
        }
        order.setUpdateDate(LocalDateTime.now());
        saveOrder(order);
    }

    @Override
    public Orders createNewOrder(Users user) {
        Orders order = new Orders();
        order.setCreationDate(LocalDateTime.now());
        order.setUpdateDate(LocalDateTime.now());
        order.setUser(user);
        order = saveOrder(order);
        return order;
    }


}