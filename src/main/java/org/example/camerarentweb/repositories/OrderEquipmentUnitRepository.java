package org.example.camerarentweb.repositories;

import org.example.camerarentweb.entities.OrderEquipmentUnit;

import java.util.List;
import java.util.Optional;

public interface OrderEquipmentUnitRepository {
    Optional<OrderEquipmentUnit> findById(int id);

    OrderEquipmentUnit save(OrderEquipmentUnit entity);

    // с пагинацией int page, int size
    List<OrderEquipmentUnit> findAll();
}
