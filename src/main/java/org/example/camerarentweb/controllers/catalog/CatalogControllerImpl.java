package org.example.camerarentweb.controllers.catalog;

import org.example.camerarentcontracts.controllers.base.BaseController;
import org.example.camerarentcontracts.controllers.catalog.CatalogController;
import org.example.camerarentcontracts.input.EquipmentTypeListInputModel;
import org.example.camerarentcontracts.viewmodel.collective.BaseViewModel;
import org.example.camerarentcontracts.viewmodel.pages.catalog.CatalogPageViewModel;
import org.example.camerarentcontracts.viewmodel.pages.catalog.EquipmentViewModel;
import org.example.camerarentweb.controllers.BaseControllerImpl;
import org.example.camerarentweb.dto.EquipmentTypeRatedCardDto;
import org.example.camerarentweb.services.EquipmentTypeDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CatalogControllerImpl extends BaseControllerImpl implements CatalogController{

    private final EquipmentTypeDomainService equipmentTypeService;

    @Autowired
    public CatalogControllerImpl(EquipmentTypeDomainService equipmentTypeService) {
        this.equipmentTypeService = equipmentTypeService;
    }

    @Override
    @GetMapping("/{categoryName}")
    public String catalogPage(
            @PathVariable String categoryName,
            @ModelAttribute("filter") EquipmentTypeListInputModel filterInputModel,
            Model model) {
        var page = filterInputModel.page() != null ? filterInputModel.page() : 0;
        var size = filterInputModel.size() != null ? filterInputModel.size() : 25;
        var lowestPrice = filterInputModel.lowestPrice() != null ? filterInputModel.lowestPrice() : 0;
        var highestPrice = filterInputModel.highestPrice() != null ? filterInputModel.highestPrice() : 999_999_999;
        List<EquipmentTypeRatedCardDto> listOfEquipmentCards = equipmentTypeService.findAllByCategoryAndPrice(
                categoryName,
                lowestPrice,
                highestPrice);
        for (int i = 0; i < listOfEquipmentCards.size(); i++) {
            System.out.println(listOfEquipmentCards.get(i).getImageUrl().toString());
        }

        List<EquipmentViewModel> equipmentViewModelList = listOfEquipmentCards.stream()
                .map(e -> new EquipmentViewModel(
                        e.getName(),
                        e.getRating(),
                        e.getPrice(),
                        e.getImageUrl()
                )).toList();

        CatalogPageViewModel catalogPageViewModel = new CatalogPageViewModel(
                createBaseViewModel ("Каталог | Категория: " + categoryName),
                "/" + categoryName,
                equipmentViewModelList);
        model.addAttribute("model", catalogPageViewModel);
        model.addAttribute("filter", filterInputModel);
        return "catalog-page";
    }

//    @Override
//    @PostMapping("/{categoryName}")
//    public String search(String categoryName, String lowestPrice, String highestPrice, CatalogPageViewModel form, Model model) {
//        return "";
//    }


}
