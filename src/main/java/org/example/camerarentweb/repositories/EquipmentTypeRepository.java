package org.example.camerarentweb.repositories;

import org.example.camerarentweb.entities.Category;
import org.example.camerarentweb.entities.EquipmentType;

import java.time.LocalDateTime;
import java.util.List;

public interface EquipmentTypeRepository extends BaseRepository<EquipmentType> {

    List<EquipmentType> findByCategory(Category category);

    List<EquipmentType> findByCategoryAndPrice(Category category, Double priceStart, Double priceEnd);

    Long countByCategory(Category category);

    EquipmentType findByName(String name);

    EquipmentType findById(String id);
}

