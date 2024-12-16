package org.example.camerarentweb.controllers.auth;

import jakarta.validation.Valid;
import org.example.camerarentcontracts.controllers.auth.RegisterController;
import org.example.camerarentcontracts.input.UserLoginInputModel;
import org.example.camerarentcontracts.input.UserRegisterInputModel;
import org.example.camerarentweb.entities.User;
import org.example.camerarentweb.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

//
//import org.example.camerarentcontracts.controllers.auth.RegisterController;
//import org.springframework.stereotype.Controller;
//
//@Controller
//public class RegisterControllerImpl implements RegisterController {
//}
@Controller
@RequestMapping("/auth")
public class RegisterControllerImpl implements RegisterController {

    private final UserService userService;

    public RegisterControllerImpl(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showLoginForm(Model model) {
        model.addAttribute("form", new UserRegisterInputModel());
        return "register-page";
    }

    @Override
    @PostMapping("/register")
    public String register(
            @Valid @ModelAttribute("form") UserRegisterInputModel form,
            BindingResult bindingResult,
            Model model
    ) {
        // Проверка ошибок валидации
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", "Пожалуйста, исправьте ошибки в форме.");
            return "register";
        }

        // Проверка совпадения паролей
        if (!form.getPassword().equals(form.getConfirmPassword())) {
            model.addAttribute("error", "Пароли не совпадают.");
            return "register";
        }

        // Проверка уникальности номера телефона
        if (!userService.isPhoneNumberUnique(form.getPhoneNumber())) {
            model.addAttribute("error", "Телефон уже используется.");
            return "register";
        }

        // Проверка уникальности email
        if (!userService.isEmailUnique(form.getEmail())) {
            model.addAttribute("error", "Почта уже используется.");
            return "register";
        }

        // Создание нового пользователя
        User newUser = new User();
        newUser.setPhoneNumber(form.getPhoneNumber());
        newUser.setEmail(form.getEmail());
        newUser.setFirstName(form.getFirstName());
        newUser.setLastName(form.getLastName());
        newUser.setPassword(form.getPassword()); // Не забудьте хешировать пароль
        userService.createUser(newUser);

        // Перенаправление на страницу входа
        return "redirect:/login";
    }
}