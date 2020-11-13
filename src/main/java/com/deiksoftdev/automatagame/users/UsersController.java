package com.deiksoftdev.automatagame.users;

import com.deiksoftdev.automatagame.register.UserEmailAlreadyExistsException;
import com.deiksoftdev.automatagame.register.UserNameAlreadyExistsException;
import com.deiksoftdev.automatagame.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class UsersController {

    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String showUsersList(Model model) {
        model.addAttribute("users", userService.findAll());
        return "user-list";
    }

    @GetMapping("/users/update/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        User user = userService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        model.addAttribute("userUpdateDTO", createUserUpdateDTO(user));
        return "user-update";
    }

    @PostMapping("/users/update/{id}")
    public String processUserUpdateForm(@PathVariable("id") long id, @Valid UserUpdateDTO userUpdateDTO,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "user-update";
        }
        try {
            userService.updateUser(id,userUpdateDTO);
        } catch (UserNameAlreadyExistsException nameExistsEx) {
            result.addError(new FieldError("userUpdateDTO", "name", "name exists already"));
            return "user-update";
        } catch (UserEmailAlreadyExistsException emailExistsEx) {
            result.addError(new FieldError("userUpdateDTO", "email", "email exists already"));
            return "user-update";
        } catch (UserByIdNotRegisteredException e) {
            new IllegalArgumentException("Invalid user Id:" + id);
        }
        return "redirect:/users";
    }


    private UserUpdateDTO createUserUpdateDTO(User user){
        var dto = new UserUpdateDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setAdmin(user.isAdmin());
        dto.setDisabled(user.isDisabled());
        return dto;
    }
}
