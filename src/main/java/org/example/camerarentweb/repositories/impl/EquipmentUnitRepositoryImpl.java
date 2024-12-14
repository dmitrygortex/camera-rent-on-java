package org.example.camerarentweb.repositories.impl;

import jakarta.persistence.TypedQuery;
import org.example.camerarentweb.entities.Category;
import org.example.camerarentweb.entities.EquipmentType;
import org.example.camerarentweb.entities.EquipmentUnit;
import org.example.camerarentweb.entities.Order;
import org.example.camerarentweb.repositories.EquipmentUnitRepository;
import org.example.camerarentweb.repositories.OrderRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class EquipmentUnitRepositoryImpl extends BaseRepositoryImpl<EquipmentUnit> implements EquipmentUnitRepository {

    public EquipmentUnitRepositoryImpl() {
        super(EquipmentUnit.class);
    }

    @Override
    public List<EquipmentUnit> findAvailableEquipmentUnitsByCategoryAndDate(Category category, LocalDateTime dateStart, LocalDateTime dateEnd) {
        return entityManager.createQuery(
                        "SELECT eu FROM EquipmentUnit eu WHERE eu.equipmentType.category = :category " +
                                "AND eu.status = 'AVAILABLE' " +
                                "AND NOT EXISTS (SELECT oeu FROM OrderEquipmentUnit oeu WHERE oeu.equipmentUnit = eu " +
                                "AND oeu.order.startDate <= :dateEnd AND oeu.order.endDate >= :dateStart)", EquipmentUnit.class)
                .setParameter("category", category)
                .setParameter("dateStart", dateStart)
                .setParameter("dateEnd", dateEnd)
                .getResultList();
    }

    @Override
    public List<EquipmentUnit> findAvailableEquipmentUnitsByType(EquipmentType equipmentType) {
        return entityManager.createQuery(
                        "SELECT eu FROM EquipmentUnit eu WHERE eu.equipmentType = :equipmentType " +
                                "AND eu.status = 'AVAILABLE'", EquipmentUnit.class)
                .setParameter("equipmentType", equipmentType)
                .getResultList();
    }
}


