package restaurantSF.finalproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import restaurantSF.finalproject.entity.Orders;


import java.util.List;

public interface OrderRepository extends JpaRepository<Orders, Integer> {
    List<Orders> findByUserId (Integer userId);
}
