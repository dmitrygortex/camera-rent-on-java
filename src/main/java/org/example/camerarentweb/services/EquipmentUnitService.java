package org.example.camerarentweb.services;

import org.example.camerarentweb.dto.EquipmentUnitAdminPageCardDto;
import org.example.camerarentweb.entities.EquipmentStatus;
import org.example.camerarentweb.entities.EquipmentUnit;
import org.example.camerarentweb.entities.OrderStatus;

import java.util.List;

public interface EquipmentUnitService {
    void changeUnitStatus(int orderId, EquipmentStatus newStatus);
    EquipmentUnit findById(int id);
    List<EquipmentUnitAdminPageCardDto> findAll(int page, int size);
    void updateEquipmentUnit(int id, String serialNumber, EquipmentStatus rentStatus, String notes);
}
