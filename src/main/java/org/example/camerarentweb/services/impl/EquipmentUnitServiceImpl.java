package org.example.camerarentweb.services.impl;

import org.example.camerarentweb.entities.EquipmentStatus;
import org.example.camerarentweb.entities.EquipmentUnit;
import org.example.camerarentweb.entities.Order;
import org.example.camerarentweb.entities.OrderStatus;
import org.example.camerarentweb.repositories.EquipmentUnitRepository;
import org.example.camerarentweb.services.EquipmentUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class EquipmentUnitServiceImpl implements EquipmentUnitService {


    @Autowired
    private EquipmentUnitRepository equipmentUnitRepository;

    @Override
    @Transactional
    public void changeUnitStatus(int unitId, EquipmentStatus newStatus) {
        Optional<EquipmentUnit> optionalEquipmentUnit = equipmentUnitRepository.findById(unitId);
        if (!optionalEquipmentUnit.isPresent()) {
            throw new IllegalArgumentException("Equipment unit not found");
        }
        EquipmentUnit equipmentUnit = optionalEquipmentUnit.get();
        equipmentUnit.setStatus(newStatus);
        equipmentUnitRepository.save(equipmentUnit);
    }

    @Override
    public EquipmentUnit findById(int id) {
        Optional<EquipmentUnit> optionalEquipmentUnit = equipmentUnitRepository.findById(id);
        return optionalEquipmentUnit.isEmpty() ? null : optionalEquipmentUnit.get();
    }

}
