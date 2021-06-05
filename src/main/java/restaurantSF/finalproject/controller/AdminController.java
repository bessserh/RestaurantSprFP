package restaurantSF.finalproject.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import restaurantSF.finalproject.DTO.OrderDTO;
import restaurantSF.finalproject.Service.DishService;
import restaurantSF.finalproject.Service.OrderService;
import restaurantSF.finalproject.Service.OrderedDishesService;
import restaurantSF.finalproject.entity.Orders;

import java.util.List;

@Controller
@AllArgsConstructor
public class AdminController {

    private final OrderService orderService;
    private final DishService dishService;
    private final OrderedDishesService orderedDishesService;


    @GetMapping(value = "/adminPage")
    public String adminPage(@AuthenticationPrincipal User userAuth, ModelMap model) {
        List<OrderDTO> orders = orderService.findAll();
        model.addAttribute("admin", userAuth.getUsername());
        model.put("orders", orders);
        model.put("orders_dishes", orderedDishesService.findAllDishes());
        model.put("dishes", dishService.findAll());
        return "adminPage";
    }


    @GetMapping(value = "/adminPage/{type}/{orderId}")
    public String userCabinetchangeItem(@PathVariable(value = "type") String type,
                                        @PathVariable(value = "orderId") Integer orderId,
                                        @AuthenticationPrincipal User userAuth,
                                        ModelMap model) {
        Orders order = orderService.findById(orderId);
        orderService.nextStatus(type, order);
        return "redirect:/adminPage";
    }
}