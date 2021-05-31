package restaurantSF.finalproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import restaurantSF.finalproject.entity.Users;

public interface UserRepository extends JpaRepository<Users, Integer> {

    Users findByEmail(String email);

}
