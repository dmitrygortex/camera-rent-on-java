package org.example.camerarentweb.services.impl;

import org.example.camerarentweb.dto.EquipmentUnitAdminPageCardDto;
import org.example.camerarentweb.entities.EquipmentStatus;
import org.example.camerarentweb.entities.EquipmentUnit;
import org.example.camerarentweb.entities.Order;
import org.example.camerarentweb.entities.OrderStatus;
import org.example.camerarentweb.repositories.EquipmentUnitRepository;
import org.example.camerarentweb.services.EquipmentUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EquipmentUnitServiceImpl implements EquipmentUnitService {

    // не забыть поменять на нормально добавление зависиомстей через конструктор!!!
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

    @Override
    public List<EquipmentUnitAdminPageCardDto> findAll(int page, int size) {
        List<EquipmentUnit> equipmentUnitList = equipmentUnitRepository.findAll(page, size);
        List<EquipmentUnitAdminPageCardDto> equipmentUnitAdminPageCardDtoList = equipmentUnitList.stream()
                .map(equipmentUnit -> new EquipmentUnitAdminPageCardDto(
                        equipmentUnit.getId(),
                        equipmentUnit.getEquipmentType().getName(),
                        equipmentUnit.getSerialNumber(),
                        equipmentUnit.getStatus().name(),
                        equipmentUnit.getNotes()
                )).toList();
        return equipmentUnitAdminPageCardDtoList;
    }

    public void updateEquipmentUnit(int id, String serialNumber, EquipmentStatus rentStatus, String notes) {
        EquipmentUnit equipmentUnit = equipmentUnitRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Оборудования с введенным вами ID не существует"));

        equipmentUnit.setSerialNumber(serialNumber);
        equipmentUnit.setStatus(rentStatus);
        equipmentUnit.setNotes(notes);

        equipmentUnitRepository.save(equipmentUnit);
    }

}
