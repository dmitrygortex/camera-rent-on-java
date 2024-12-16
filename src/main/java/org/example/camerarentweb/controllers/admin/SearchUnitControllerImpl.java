//package org.example.camerarentweb.controllers.admin;
//
//import org.example.camerarentcontracts.input.ApplyRentStatusAndAddNote;
//import org.example.camerarentcontracts.viewmodel.pages.admin.adminsearch.AllUnitsPageViewModel;
//import org.example.camerarentcontracts.viewmodel.pages.admin.adminsearch.UnitInSearchListViewModel;
//import org.example.camerarentweb.dto.EquipmentUnitAdminPageCardDto;
//import org.springframework.stereotype.Controller;
//import org.example.camerarentweb.controllers.BaseControllerImpl;
//import org.example.camerarentweb.services.EquipmentUnitService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import java.util.List;
//
//@Controller
//@RequestMapping("/admin-search")
//public class SearchUnitControllerImpl extends BaseControllerImpl {
//
//    private final EquipmentUnitService equipmentUnitService;
//
//    @Autowired
//    public SearchUnitControllerImpl(EquipmentUnitService equipmentUnitService) {
//        this.equipmentUnitService = equipmentUnitService;
//    }
//
//    @GetMapping
//    public String adminSearchPage(Model model) {
//        model.addAttribute("baseViewModel", createBaseViewModel("Поиск оборудования"));
//        List<EquipmentUnitAdminPageCardDto> equipmentUnitAdminPageCardDtoList = equipmentUnitService.findAll(0,100);
//        List<UnitInSearchListViewModel> unitInSearchList = equipmentUnitAdminPageCardDtoList.stream()
//                .map(u -> new UnitInSearchListViewModel(
//                        u.id(),
//                        u.serialNumber(),
//                        u.name(),
//                        u.status(),
//                        u.notes()
//                )).toList();
//        AllUnitsPageViewModel allUnitsPageViewModel = new AllUnitsPageViewModel(
//                createBaseViewModel("Управление оборудованием"),
//                unitInSearchList
//        );
//        model.addAttribute("model", allUnitsPageViewModel);
//        return "admin-all-units-page";
//    }
//
//    @PostMapping
//    public String adminSearchPageWithId(@ModelAttribute("form") ApplyRentStatusAndAddNote form, Model model) {
//        // добавить потом пагинацию тут тоже
//
//
//        //model.addAttribute()
//        return "admin-all-units-page";
//    }
//}