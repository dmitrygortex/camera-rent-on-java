package org.example.camerarentweb.services;

import org.example.camerarentweb.dto.EquipmentTypeRatedCardDto;

public interface EquipmentTypeService {
    EquipmentTypeRatedCardDto findByName(String name);
}
