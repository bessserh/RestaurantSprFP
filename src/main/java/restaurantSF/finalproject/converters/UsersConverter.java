package restaurantSF.finalproject.converters;

import org.springframework.stereotype.Component;
import restaurantSF.finalproject.DTO.UsersDTO;
import restaurantSF.finalproject.entity.Users;

@Component
public class UsersConverter {

    public Users fromUserDtoToUser(UsersDTO usersDto) {
        Users users = new Users();
        users.setId(usersDto.getId());
        users.setEmail(usersDto.getEmail());
        users.setPassword(usersDto.getPassword());
        users.setRole(usersDto.getRole());
        users.setActive(usersDto.getActive());
        return users;
    }

    public UsersDTO fromUserToUserDto(Users users) {
        return UsersDTO.builder()
                .id(users.getId())
                .email(users.getEmail())
                .password(users.getPassword())
                .active(users.getActive())
                .role(users.getRole())
                .build();
    }
}
