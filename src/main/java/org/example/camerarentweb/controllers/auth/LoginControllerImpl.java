package org.example.camerarentweb.controllers.auth;

import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.camerarentcontracts.input.UserLoginInputModel;
import org.example.camerarentweb.entities.User;
import org.example.camerarentweb.services.impl.AuthService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

//@Controller
//@RequestMapping("/auth")
//public class LoginControllerImpl implements LoginController {
//
//    @GetMapping("/login")
//    public String showLoginForm(Model model) {
//        model.addAttribute("form", new UserLoginInputModel());
//        return "login-page";
//    }
//
//    @Override
//    @PostMapping("/login")
//    public String login(
//            @Valid @ModelAttribute("form") UserLoginInputModel form,
//            BindingResult bindingResult,
//            Model model
//    ) {
//        if (bindingResult.hasErrors()) {
//            return "login-page";
//        }
//        return "index";
//    }
//}

@Controller
@RequestMapping("/auth")
public class LoginControllerImpl{

    private final AuthService authService;
    private static final Logger LOG = LogManager.getLogger(Controller.class);

    public LoginControllerImpl(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/login")
    public String showLoginForm(@RequestParam(name = "error", required = false) String error, Model model) {
        System.out.println(error);
        //model.addAttribute("form", new UserLoginInputModel());
        System.out.println("get method login");
        return "login-page";
    }

//    @PostMapping("/login")
//    public String login(
//            //@Valid
//            @ModelAttribute("form") UserLoginInputModel form//,
//            //BindingResult bindingResult,
//            //Model model,
//            //RedirectAttributes redirectAttributes
//    ) {
//
//        System.out.println("post method login");
////        if (bindingResult.hasErrors()) {
////            System.out.println("\n\n\n\n\n");
////            System.out.println("Error in login-page (bindingResult.hasErrors()):  " + bindingResult.toString());
////            return "redirect:/error/login";
////        }
//
//        // Mock login validation, replace with actual authentication logic
//        boolean loginSuccess = true;
//        if (!loginSuccess) {
//            System.out.println("not successful login login-page");
//            //redirectAttributes.addFlashAttribute("badCredentials", true);
//            //redirectAttributes.addFlashAttribute("username", form.getEmail());
//            return "redirect:/error/login";
//        }
//        System.out.println("successful login login-page");
//        return "index";
//    }
//    @PostMapping("/login")
//    public String login(
//            @Valid @ModelAttribute("form") UserLoginInputModel form,
//            //BindingResult bindingResult,
//            Model model
////            ,
//            //RedirectAttributes redirectAttributes
//    ) {
//        System.out.println("post method login");
////        if (bindingResult.hasErrors()) {
////            return "login-page";
////        }
//
//        try {
//            System.out.println("Username: " + form.getUsername());
//            System.out.println("Password: " + form.getPassword());
//            User authenticatedUser = authService.authenticate(form);
//
//            SecurityContextHolder.getContext().setAuthentication(
//                    new UsernamePasswordAuthenticationToken(authenticatedUser.getEmail(), form.getPassword())
//            );
//
//            return "redirect:/";
//        } catch (Exception e) {
//           // redirectAttributes.addFlashAttribute("error", "Invalid username or password");
//            return "redirect:/auth/login";
//        }
//    }

    @PostMapping("/login-error")
    public String onFailedLogin(
            @ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY) String username,
            RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY, username);
        redirectAttributes.addFlashAttribute("badCredentials", true);

        return "error/login";
    }

}