package org.example.camerarentweb.controllers.auth;

import org.example.camerarentcontracts.viewmodel.pages.auth.AccountPageViewModel;
import org.example.camerarentweb.controllers.BaseControllerImpl;
import org.example.camerarentweb.entities.User;
import org.example.camerarentweb.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/account")
public class AccountControllerImpl extends BaseControllerImpl {

    private final UserService userService;

    public AccountControllerImpl(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public String getAccountPage(@PathVariable int userId, Model model) {
        Optional<User> userOptional = userService.getUserById(userId);

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            // подумать над тем чтобы добавить как то деактивацию пользователя (чтобы можно было банить)
            AccountPageViewModel accountPageViewModel = new AccountPageViewModel(
                    createBaseViewModel("Account | " + user.getFirstName()),
                    user.getFirstName() + " " + user.getLastName(),
                    user.getEmail(),
                    user.getRole().toString()
            );

            model.addAttribute("model", accountPageViewModel);

        } else {

            return "error/404";
        }

        return "account-page";
    }
}