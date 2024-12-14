package org.example.camerarentweb.controllers.auth;

import jakarta.validation.Valid;
import org.example.camerarentcontracts.controllers.auth.LoginController;
import org.example.camerarentcontracts.input.UserLoginInputModel;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

@Controller
public class LoginControllerImpl implements LoginController {

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("form", new UserLoginInputModel());
        return "login";
    }

    @Override
    @PostMapping("/login")
    public String login(
            @Valid @ModelAttribute("form") UserLoginInputModel form,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            return "login";
        }
        return "index";
    }
}