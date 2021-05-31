package restaurantSF.finalproject.Service;

import restaurantSF.finalproject.DTO.UsersDTO;
import restaurantSF.finalproject.errorValid.ValidationException;
import java.util.List;


public interface UsersService {

    UsersDTO saveUser(UsersDTO usersDto) throws ValidationException;

    void deleteUser(Integer userId);

    UsersDTO findByEmail(String email);

    List<UsersDTO> findAll();

}
