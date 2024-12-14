package org.example.camerarentweb.services;

import org.example.camerarentweb.entities.EquipmentStatus;
import org.example.camerarentweb.entities.EquipmentUnit;
import org.example.camerarentweb.entities.OrderStatus;

public interface EquipmentUnitService {
    void changeUnitStatus(int orderId, EquipmentStatus newStatus);
    EquipmentUnit findById(int id);}
