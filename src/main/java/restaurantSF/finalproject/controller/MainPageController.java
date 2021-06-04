package restaurantSF.finalproject.controller;


import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import restaurantSF.finalproject.Service.DishService;
import restaurantSF.finalproject.Service.OrderService;
import restaurantSF.finalproject.entity.Dishes;
import restaurantSF.finalproject.entity.Enums.Category;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
@RequestMapping
public class MainPageController {

    private final DishService dishService;
    private final OrderService orderService;


    @GetMapping(value = "/")
    public String mainPage(Model model) {
        return mainPagePaging(1, "name", "asc", "all", model);
    }


    @GetMapping(value = "page/{pageNo}")
    public String mainPagePaging(@PathVariable("pageNo") int pageNo,
                                 @RequestParam("sortField") String sortField,
                                 @RequestParam("sortDir") String sortDir,
                                 @RequestParam("sortCat") String sortCat,
                                 Model model) {
        int pageSize = 4;
        Page<Dishes> dishesPage = dishService.findPaginated(pageNo, pageSize, sortField, sortDir);

        List<Dishes> dishes = dishService.paginatedCategory(dishesPage, sortCat);

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", dishesPage.getTotalPages());
        model.addAttribute("totalItems", dishesPage.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("sortCat", sortCat);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("dishes", dishes);
        return "mainPage";
    }

    @GetMapping(value = "/task")
    public String taskList(ModelMap model) {
        model.put("users", orderService.findAll());
        return "task";
    }
}