package org.example.camerarentweb.controllers.auth;

import jakarta.validation.Valid;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.camerarentcontracts.controllers.auth.RegisterController;
import org.example.camerarentcontracts.input.UserLoginInputModel;
import org.example.camerarentcontracts.input.UserRegisterInputModel;
import org.example.camerarentweb.entities.User;
import org.example.camerarentweb.services.UserService;
import org.example.camerarentweb.services.impl.AuthService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

//
//import org.example.camerarentcontracts.controllers.auth.RegisterController;
//import org.springframework.stereotype.Controller;
//
//@Controller
//public class RegisterControllerImpl implements RegisterController {
//}
@Controller
@RequestMapping("/auth")
public class RegisterControllerImpl implements RegisterController
{

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthService authService;
    private static final Logger LOG = LogManager.getLogger(Controller.class);

    public RegisterControllerImpl(UserService userService,
                                  PasswordEncoder passwordEncoder,
                                  AuthService authService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.authService = authService;
    }

    @GetMapping("/register")
    public String showLoginForm(
            Principal principal,
            Model model) {

        //вынести в сервис
        String userName;
        if (principal == null) {
            userName = "anonymous";
        }
        else {
            userName = principal.getName();
        }

        System.out.println("REGISTER PAGE GET BY: " + userName);
        LOG.log(Level.INFO, "Register page requested by " + userName);

        model.addAttribute("form", new UserRegisterInputModel());
        return "register-page";
    }

    @PostMapping("/register")
    public String doRegister(
            Principal principal,
            @Valid UserRegisterInputModel userRegisterInputModel,
            //BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        //вынести в сервис
        String userName;
        if (principal == null) {
            userName = "anonymous";
        }
        else {
            userName = principal.getName();
        }

        System.out.println("REGISTER PROCESS BY: " + userName);
        LOG.log(Level.INFO, "Register process started by " + userName);


        System.out.println("register method called");
        System.out.println("userRegisterInputModel:\n"
        + userRegisterInputModel.toString()+ "\n"+userRegisterInputModel.getPhoneNumber() +"\n"+userRegisterInputModel.getPassword());


//        if (bindingResult.hasErrors()) {
//            redirectAttributes.addFlashAttribute("userRegisterInputModel", userRegisterInputModel);
//            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterInputModel", bindingResult);
//            System.out.println("/register post has binding errors");
//            return "redirect:/auth/register";
//        }

        this.authService.register(userRegisterInputModel);

        return "redirect:/auth/login";
    }

//    @Override
//    @PostMapping("/register")
//    public String register(
//            @Valid @ModelAttribute("form") UserRegisterInputModel form,
//            BindingResult bindingResult,
//            Model model
//    ) {
//        if (bindingResult.hasErrors()) {
//            model.addAttribute("error", "Пожалуйста, исправьте ошибки в форме.");
//            return "redirect:/register";
//        }
//        if (!form.getPassword().equals(form.getConfirmPassword())) {
//            model.addAttribute("error", "Пароли не совпадают.");
//            return "redirect:/register";
//        }
//        if (!userService.isPhoneNumberUnique(form.getPhoneNumber())) {
//            model.addAttribute("error", "Телефон уже используется.");
//            return "redirect:/register";
//        }
//        if (!userService.isEmailUnique(form.getEmail())) {
//            model.addAttribute("error", "Почта уже используется.");
//            return "redirect:/register";
//        }
//        User newUser = new User();
//        newUser.setPhoneNumber(form.getPhoneNumber());
//        newUser.setEmail(form.getEmail());
//        newUser.setFirstName(form.getFirstName());
//        newUser.setLastName(form.getLastName());
//        newUser.setPassword(passwordEncoder.encode(form.getPassword()));
//        userService.createUser(newUser);
//        return "redirect:/login";
//    }
}