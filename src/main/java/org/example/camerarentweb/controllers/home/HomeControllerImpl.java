package org.example.camerarentweb.controllers.home;

import org.example.camerarentcontracts.controllers.home.HomeController;
import org.example.camerarentcontracts.viewmodel.pages.catalog.EquipmentViewModel;
import org.example.camerarentcontracts.viewmodel.pages.main.MainPageViewModel;
import org.example.camerarentcontracts.viewmodel.pages.main.sections.EquipmentSalesSectionViewModel;
import org.example.camerarentcontracts.viewmodel.pages.main.sections.EquipmentSetsSectionViewModel;
import org.example.camerarentcontracts.viewmodel.pages.main.sections.PopularEquipmentSectionViewModel;
import org.example.camerarentweb.controllers.BaseControllerImpl;
import org.example.camerarentweb.dto.EquipmentTypeRatedCardDto;
import org.example.camerarentweb.services.EquipmentTypeDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.LogManager;
//import java.util.logging.Logger;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Controller
@RequestMapping("/")
public class HomeControllerImpl extends BaseControllerImpl implements HomeController {

    private final EquipmentTypeDomainService equipmentTypeService;
    private static final Logger LOG = LogManager.getLogger(Controller.class);


    @Autowired
    public HomeControllerImpl(EquipmentTypeDomainService equipmentTypeService) {
        this.equipmentTypeService = equipmentTypeService;
    }

    @Override
    @GetMapping()
    public String index(Principal principal,
            Model model
    ) {

        //вынести в сервис
        String userName;
        if (principal == null) {
            userName = "anonymous";
        }
        else {
            userName = principal.getName();
        }

        System.out.println("HOME PAGE WAS VISITED BY: " + userName);
        LOG.log(Level.INFO, "Show Home Page for " + userName);

        List<EquipmentTypeRatedCardDto> listOfPopularEquipmentCards = equipmentTypeService.findTopMostPopular(5);
        System.out.println("listOfPopularEquipmentCards.size(): " + listOfPopularEquipmentCards.size());
        List<EquipmentViewModel> popularEquipmentViewModelList = listOfPopularEquipmentCards.stream()
                .map(e -> new EquipmentViewModel(
                        e.getName(),
                        e.getRating(),
                        e.getPrice(),
                        e.getImageUrl()
                )).toList();
        List<EquipmentTypeRatedCardDto> equipmentSetsList = equipmentTypeService.findEquipmentKits(5);
        System.out.println("equipmentSetsList.size()" + equipmentSetsList.size());
        List<EquipmentViewModel> equipmentSetsViewModelList = equipmentSetsList.stream()
                .map(e -> new EquipmentViewModel(
                        e.getName(),
                        e.getRating(),
                        e.getPrice(),
                        e.getImageUrl()
                )).toList();
        List<EquipmentTypeRatedCardDto> equipmentSalesList = equipmentTypeService.findEquipmentSales(5);
        List<EquipmentViewModel> equipmentSalesViewModelList = equipmentSalesList.stream()
                .map(e -> new EquipmentViewModel(
                        e.getName(),
                        e.getRating(),
                        e.getPrice(),
                        e.getImageUrl()
                )).toList();
        System.out.println(">>>>>\n\n\n\n\n>>>>>" + popularEquipmentViewModelList.size() + "\n\n\n\n");

        MainPageViewModel mainPageViewModel = new MainPageViewModel(
                createBaseViewModel("Camera Rent | by Dmitry Gortex"),
                "https://sun9-39.userapi.com/impg/pQrM75AF0JpXL4XjhKKENqDs8hBYmmmMNd-xAw/V5CSexfZO-M.jpg?size=1905x713&quality=95&sign=9076fda714b23eaa1dc788763a58ac29&type=album",
                new PopularEquipmentSectionViewModel(popularEquipmentViewModelList),
                new EquipmentSetsSectionViewModel(equipmentSetsViewModelList),
                new EquipmentSalesSectionViewModel(equipmentSalesViewModelList)
                );
        model.addAttribute("model", mainPageViewModel);
        return "index";
    }
}
