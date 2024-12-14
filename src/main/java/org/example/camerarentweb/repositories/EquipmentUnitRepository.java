package org.example.camerarentweb.repositories;

import org.example.camerarentweb.entities.Category;
import org.example.camerarentweb.entities.EquipmentType;
import org.example.camerarentweb.entities.EquipmentUnit;
import org.example.camerarentweb.entities.Order;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface EquipmentUnitRepository extends BaseRepository<EquipmentUnit> {

    List<EquipmentUnit> findAvailableEquipmentUnitsByCategoryAndDate(Category category, LocalDateTime dateStart, LocalDateTime dateEnd);

    List<EquipmentUnit> findAvailableEquipmentUnitsByType(EquipmentType equipment);

    //Optional<EquipmentUnit> findById(int id);
}
