package org.example.camerarentweb.services.impl;

import org.example.camerarentweb.dto.EquipmentTypeRatedCardDto;
import org.example.camerarentweb.repositories.CategoryRepository;
import org.example.camerarentweb.repositories.EquipmentTypeRepository;
import org.example.camerarentweb.services.EquipmentTypeService;
//import org.example.camerarentweb.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EquipmentTypeServiceImpl implements EquipmentTypeService {
    private final EquipmentTypeRepository equipmentTypeRepository;
    @Autowired
    public EquipmentTypeServiceImpl(EquipmentTypeRepository equipmentTypeRepository) {
        this.equipmentTypeRepository = equipmentTypeRepository;
    }

    @Override
    public EquipmentTypeRatedCardDto findByName(String name) {
        return null;
    }
}
