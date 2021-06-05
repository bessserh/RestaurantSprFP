package restaurantSF.finalproject.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import restaurantSF.finalproject.DTO.UsersDTO;
import restaurantSF.finalproject.Service.DishService;
import restaurantSF.finalproject.Service.OrderService;
import restaurantSF.finalproject.Service.OrderedDishesService;
import restaurantSF.finalproject.Service.UsersService;
import restaurantSF.finalproject.converters.UsersConverter;
import restaurantSF.finalproject.entity.Dishes;
import restaurantSF.finalproject.entity.Enums.OrderStatus;
import restaurantSF.finalproject.entity.OrderedDishes;
import restaurantSF.finalproject.entity.Orders;

import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
public class UserOrderController {

    private final UsersService usersService;
    private final UsersConverter usersConverter;
    private final OrderedDishesService orderedDishesService;
    private final DishService dishService;
    private final OrderService orderService;

    @GetMapping(value = "/userCabinet")
    public String userCabinet(@AuthenticationPrincipal User userAuth, ModelMap model) {

        UsersDTO user = usersService.findByEmail(userAuth.getUsername());
        Orders order = orderService.findOrdersByUserId(user.getId());
        if (order != null) {
            List<OrderedDishes> ordersDishes = orderedDishesService.findOrderDishesByOrder_id(order.getId());

            List<Dishes> dishes = new ArrayList<>();
            for (OrderedDishes od : ordersDishes) {
                Dishes dish = dishService.findDishById(od.getDish_id());
                if (dish != null) {
                    dishes.add(dish);
                }
            }
            model.addAttribute("dishes", dishes);
            model.addAttribute("orders_dishes", ordersDishes);
            model.addAttribute("order", order);

        } else {
            model.addAttribute("dishes", null);
            model.addAttribute("orders_dishes", null);
            model.addAttribute("order", null);
            model.addAttribute("mes", "nothing");

        }

        model.addAttribute("user", userAuth.getUsername());
        return "userCabinet";
    }

    @GetMapping(value = "/userCabinet/{dishId}")
    public String userCabinetAddOrder(@PathVariable(value = "dishId") Integer dishId,
                                      @AuthenticationPrincipal User userAuth,
                                      ModelMap model) {
        UsersDTO user = usersService.findByEmail(userAuth.getUsername());
        Orders order = orderService.findOrdersByUserId(user.getId());
        if (order == null) {
            order = orderService.createNewOrder(usersConverter.fromUserDtoToUser(user));
        }

        if (order.getStatus().equals(OrderStatus.MAKING)) {
            orderedDishesService.addOrderDishToOrder(order.getId(), dishId);
        }
        return "redirect:/userCabinet";
    }

    @GetMapping(value = "/userCabinet/{type}/{dishId}")
    public String userCabinetChangeItem(@PathVariable(value = "type") String type,
                                        @PathVariable(value = "dishId") Integer dishId,
                                        @AuthenticationPrincipal User userAuth,
                                        ModelMap model) {
        UsersDTO user = usersService.findByEmail(userAuth.getUsername());
        Orders order = orderService.findOrdersByUserId(user.getId());
        orderedDishesService.changeAmount(order.getId(), dishId, type);
        return "redirect:/userCabinet";
    }

    @GetMapping(value = "/approve/{orderId}")
    public String approveOrder(@PathVariable(value = "orderId") Integer orderId,
                               ModelMap model) {
        Orders order = orderService.findById(orderId);
        order.setStatus(OrderStatus.APPROVING);
        orderService.saveOrder(order);
        return "redirect:/userCabinet";
    }

    @GetMapping(value = "/remove/{orderId}/{dishId}")
    public String removeOrderDish(@PathVariable(value = "orderId") Integer orderId,
                                  @PathVariable(value = "dishId") Integer dishId,
                                  ModelMap model) {
        OrderedDishes orderedDish = orderedDishesService.findOrderDishesByOrderAndDishId(orderId, dishId);
        orderedDishesService.deleteOrderDish(orderedDish);
        return "redirect:/userCabinet";
    }
}