package org.example.camerarentweb.services;

import org.example.camerarentweb.dto.EquipmentTypeCardDto;
import org.example.camerarentweb.dto.EquipmentTypeRatedCardDto;

import java.util.List;

public interface EquipmentTypeDomainService {
    List<EquipmentTypeRatedCardDto> findAllByCategoryAndPrice(String category, double lowestPrice, double highestPrice);
    List<EquipmentTypeRatedCardDto> findTopMostPopular(int cardsNumber);
    List<EquipmentTypeRatedCardDto> findEquipmentKits(int cardsNumber);
    List<EquipmentTypeRatedCardDto> findEquipmentSales(int cardsNumber);
    EquipmentTypeCardDto findByName(String name);
}
