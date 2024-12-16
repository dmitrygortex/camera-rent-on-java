package org.example.camerarentweb.controllers.admin;

import org.example.camerarentcontracts.controllers.admin.AdminPanelController;
import org.example.camerarentweb.controllers.BaseControllerImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin-panel")
public class AdminPanelControllerImpl extends BaseControllerImpl implements AdminPanelController {

    @GetMapping()
    public String getAdminPanel(Model model){
        model.addAttribute("model", createBaseViewModel("♛ ADMIN PANEL ♛"));
        return "admin-page";
    }
}
