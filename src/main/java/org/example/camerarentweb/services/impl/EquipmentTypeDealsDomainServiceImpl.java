package org.example.camerarentweb.services.impl;

import org.example.camerarentweb.dto.EquipmentTypeCardDto;
import org.example.camerarentweb.dto.EquipmentTypeRatedCardDto;
import org.example.camerarentweb.entities.EquipmentType;
import org.example.camerarentweb.repositories.CategoryRepository;
import org.example.camerarentweb.repositories.EquipmentTypeRepository;
import org.example.camerarentweb.services.EquipmentTypeDomainService;
import org.example.camerarentweb.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@EnableCaching
public class EquipmentTypeDealsDomainServiceImpl implements EquipmentTypeDomainService {

    private final EquipmentTypeRepository equipmentTypeRepository;
    private final CategoryRepository categoryRepository;
    private final ReviewService reviewService;


    @Autowired
    public EquipmentTypeDealsDomainServiceImpl(EquipmentTypeRepository equipmentTypeRepository,
                                               CategoryRepository categoryRepository,
                                               ReviewService reviewService) {
        this.equipmentTypeRepository = equipmentTypeRepository;
        this.categoryRepository = categoryRepository;
        this.reviewService = reviewService;

    }

    @Override
    @Cacheable(value = "equipmentTypesByCategoryAndPrice", key = "#category + '-' + #lowestPrice + '-' + #highestPrice")
    public List<EquipmentTypeRatedCardDto> findAllByCategoryAndPrice(String category,
                                                                     double lowestPrice,
                                                                     double highestPrice) {
        List<EquipmentType> equipmentTypes = equipmentTypeRepository.findByCategoryAndPrice(
                categoryRepository.categoryByName(category),
                lowestPrice,
                highestPrice);

        return equipmentTypes.stream()
                .map(this::mapToRatedDto)
                .collect(Collectors.toList());
    }

    private EquipmentTypeRatedCardDto mapToRatedDto(EquipmentType equipmentType) {
        EquipmentTypeRatedCardDto equipmentTypeRatedCardDto = new EquipmentTypeRatedCardDto();
        equipmentTypeRatedCardDto.setName(equipmentType.getName());
        equipmentTypeRatedCardDto.setPrice(equipmentType.getPrice());
        equipmentTypeRatedCardDto.setImageUrl(equipmentType.getPhoto());
        equipmentTypeRatedCardDto.setRating(reviewService.calculateEquipmentTypeRating(equipmentType.getId()));
        return equipmentTypeRatedCardDto;
    }


    // подумать как оптимизировать
    @Override
    @Cacheable(value = "mostPopular", key = "#cardsNumber")
    public List<EquipmentTypeRatedCardDto> findTopMostPopular(int cardsNumber) {
        List<EquipmentType> allEquipmentTypes = equipmentTypeRepository.findAll(0, 999);

        return allEquipmentTypes.stream()
                .sorted((e1, e2) -> Double.compare(
                        reviewService.calculateEquipmentTypeRating(e2.getId()),
                        reviewService.calculateEquipmentTypeRating(e1.getId())))
                .limit(cardsNumber)
                .map(this::mapToRatedDto)
                .collect(Collectors.toList());
    }

    @Override
    @Cacheable(value = "equipmentKits", key = "#cardsNumber")
    public List<EquipmentTypeRatedCardDto> findEquipmentKits(int cardsNumber) {
        var equipmentTypesList = equipmentTypeRepository.findByCategory(categoryRepository.categoryByName("readyKits"));
        return equipmentTypesList.stream()
                .map(this::mapToRatedDto)
                .collect(Collectors.toList());
    }

    @Override
    @Cacheable(value = "equipmentSales", key = "#cardsNumber")
    public List<EquipmentTypeRatedCardDto> findEquipmentSales(int cardsNumber) {
        List<EquipmentType> allEquipmentTypes = equipmentTypeRepository.findAll(0, 999);

        return allEquipmentTypes.stream()
                .sorted((e1, e2) -> Double.compare(
                        reviewService.calculateEquipmentTypeRating(e1.getId()),
                        reviewService.calculateEquipmentTypeRating(e2.getId())))
                .limit(cardsNumber)
                .map(this::mapToRatedDto)
                .collect(Collectors.toList());
    }

    @Override
    @Cacheable(value = "equipmentTypeByName", key = "#name")
    public EquipmentTypeCardDto findByName(String name) {
        EquipmentType equipmentType = equipmentTypeRepository.findByName(name);
        if (equipmentType == null) {
            return null;
        }
        return mapToTypeCardDto(equipmentType);
    }

    private EquipmentTypeCardDto mapToTypeCardDto(EquipmentType equipmentType) {
        EquipmentTypeCardDto equipmentTypeCardDto = new EquipmentTypeCardDto();
        equipmentTypeCardDto.setId(equipmentType.getId());
        equipmentTypeCardDto.setName(equipmentType.getName());
        equipmentTypeCardDto.setDescription(equipmentType.getDescription());
        equipmentTypeCardDto.setPricePerDay(
                Double.parseDouble(String.format("%.2f", equipmentType.getPrice())
                        .replace(",", ".")));
        equipmentTypeCardDto.setImageUrl(equipmentType.getPhoto());
        equipmentTypeCardDto.setRating(reviewService.calculateEquipmentTypeRating(equipmentType.getId()));
        equipmentTypeCardDto.setCharacterization(equipmentType.getDescription());
        equipmentTypeCardDto.setCategory(equipmentType.getCategory().getName());
        return equipmentTypeCardDto;
    }
}


