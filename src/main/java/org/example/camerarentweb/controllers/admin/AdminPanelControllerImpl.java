package org.example.camerarentweb.controllers.admin;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.camerarentcontracts.controllers.admin.AdminPanelController;
import org.example.camerarentweb.controllers.BaseControllerImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/admin-panel")
public class AdminPanelControllerImpl extends BaseControllerImpl implements AdminPanelController {

    private static final Logger LOG = LogManager.getLogger(Controller.class);

    @GetMapping()
    public String getAdminPanel(
            Principal principal,
            Model model){

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



        model.addAttribute("model", createBaseViewModel("♛ ADMIN PANEL ♛"));
        return "admin-page";
    }
}
