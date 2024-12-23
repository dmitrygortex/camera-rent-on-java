package org.example.camerarentweb.controllers.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.camerarentcontracts.input.ApplyRentStatusAndAddNote;
import org.example.camerarentcontracts.viewmodel.pages.admin.adminsearch.AllUnitsPageViewModel;
import org.example.camerarentcontracts.viewmodel.pages.admin.adminsearch.UnitInSearchListViewModel;
import org.example.camerarentweb.controllers.BaseControllerImpl;
import org.example.camerarentweb.dto.EquipmentUnitAdminPageCardDto;
import org.example.camerarentweb.entities.EquipmentStatus;
import org.example.camerarentweb.services.EquipmentUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin-edit")
public class EditUnitControllerImpl extends BaseControllerImpl {

    private final EquipmentUnitService equipmentUnitService;
    private static final Logger LOG = LogManager.getLogger(Controller.class);

    @Autowired
    public EditUnitControllerImpl(EquipmentUnitService equipmentUnitService) {
        this.equipmentUnitService = equipmentUnitService;
    }

    @PostMapping
    public String editUnit(@ModelAttribute("form") ApplyRentStatusAndAddNote form, Model model) {
        EquipmentStatus rentStatus = switch (form.rentStatus()) {
            case "available" -> EquipmentStatus.AVAILABLE;
            case "reserved"-> EquipmentStatus.RESERVED;
            case "rented" -> EquipmentStatus.RENTED_OUT;
            case "on_maintenance" -> EquipmentStatus.ON_MAINTENANCE;
            case "unavailable" -> EquipmentStatus.UNAVAILABLE;
            case "in_delivery" -> EquipmentStatus.IN_DELIVERY;
            case "decommissioned" -> EquipmentStatus.DECOMMISSIONED;
            default -> throw new IllegalStateException("Unexpected value: " + form.rentStatus());
        };

        System.out.println("form.id() in all units admin page: " + form.id());
        System.out.println("form.note() in all units admin page: " + form.note());
        equipmentUnitService.updateEquipmentUnit(
                Integer.parseInt(form.id() == null ? "1" : form.id()),
                form.serialNumber(),
                rentStatus,
                form.note()
        );

        List<EquipmentUnitAdminPageCardDto> updatedList = equipmentUnitService.findAll(0, 100);
        List<UnitInSearchListViewModel> unitInSearchList = updatedList.stream()
                .map(u -> new UnitInSearchListViewModel(
                        u.id(),
                        u.serialNumber(),
                        u.name(),
                        u.status(),
                        u.notes()
                )).toList();

        model.addAttribute("model", new AllUnitsPageViewModel(
                createBaseViewModel("Управление оборудованием"),
                unitInSearchList
        ));
        return "admin-all-units-page";
    }

    @GetMapping
    public String adminSearchPage(Model model) {
        model.addAttribute("baseViewModel", createBaseViewModel("Поиск оборудования"));
        List<EquipmentUnitAdminPageCardDto> equipmentUnitAdminPageCardDtoList = equipmentUnitService.findAll(0,100);
        List<UnitInSearchListViewModel> unitInSearchList = equipmentUnitAdminPageCardDtoList.stream()
                .map(u -> new UnitInSearchListViewModel(
                        u.id(),
                        u.serialNumber(),
                        u.name(),
                        u.status(),
                        u.notes()
                )).toList();
        AllUnitsPageViewModel allUnitsPageViewModel = new AllUnitsPageViewModel(
                createBaseViewModel("Управление оборудованием"),
                unitInSearchList
        );
        model.addAttribute("model", allUnitsPageViewModel);
        return "admin-all-units-page";
    }
}