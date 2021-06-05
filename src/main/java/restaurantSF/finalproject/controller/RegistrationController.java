package restaurantSF.finalproject.controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import restaurantSF.finalproject.DTO.UsersDTO;
import restaurantSF.finalproject.Service.UsersService;
import restaurantSF.finalproject.converters.UsersConverter;
import restaurantSF.finalproject.entity.Users;
import restaurantSF.finalproject.errorValid.ValidationException;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
public class RegistrationController {

    private final UsersService usersService;
    private final UsersConverter usersConverter;
    PasswordEncoder passwordEncoder;
    @Autowired
    public void setDependencyB(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping(value = "/registration")
    public String registrationForm(Model model) {
        model.addAttribute("userForm", new Users());
        return "registration";
    }

    @PostMapping(value = "/registration")
    public String registrationSubmit(@ModelAttribute @Valid Users user, ModelMap model) {

        UsersDTO userFromDb = usersService.findByEmail(user.getEmail());

        if (userFromDb != null) {
            model.addAttribute("message", "User exists!");
            return "registration";
        }

        String password = user.getPassword();
        user.setPassword(passwordEncoder.encode(password));

        try {
            usersService.saveUser(usersConverter.fromUserToUserDto(user));
        }
        catch (ValidationException e) {
            model.addAttribute("message", "Not valid");
            return "registration";
        }
        return "redirect:/";
    }

}
