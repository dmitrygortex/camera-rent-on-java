package org.example.camerarentweb.repositories.impl;

import jakarta.persistence.TypedQuery;
import org.example.camerarentweb.entities.Category;
import org.example.camerarentweb.entities.EquipmentType;
import org.example.camerarentweb.entities.Order;
import org.example.camerarentweb.repositories.EquipmentTypeRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EquipmentTypeRepositoryImpl extends BaseRepositoryImpl<EquipmentType> implements EquipmentTypeRepository {

    public EquipmentTypeRepositoryImpl() {
        super(EquipmentType.class);
    }

    @Override
    public List<EquipmentType> findByCategory(Category category) {
        return entityManager.createQuery(
                        "SELECT e FROM EquipmentType e WHERE e.category = :category AND e.deleted = false", EquipmentType.class)
                .setParameter("category", category)
                .getResultList();
    }

    @Override
    public List<EquipmentType> findByCategoryAndPrice(Category category, Double priceStart, Double priceEnd) {
        return entityManager.createQuery(
                        "SELECT e FROM EquipmentType e WHERE e.category = :category AND e.price BETWEEN :priceStart AND :priceEnd AND e.deleted = false", EquipmentType.class)
                .setParameter("category", category)
                .setParameter("priceStart", priceStart)
                .setParameter("priceEnd", priceEnd)
                .getResultList();
    }

    @Override
    public Long countByCategory(Category category) {
        return entityManager.createQuery(
                        "SELECT COUNT(e) FROM EquipmentType e WHERE e.category = :category AND e.deleted = false", Long.class)
                .setParameter("category", category)
                .getSingleResult();
    }

    @Override
    public EquipmentType findByName(String name) {
        List<EquipmentType> results = entityManager.createQuery(
                        "SELECT e FROM EquipmentType e WHERE e.name = :name AND e.deleted = false", EquipmentType.class)
                .setParameter("name", name)
                .getResultList();
        return results.isEmpty() ? null : results.get(0);
    }

    @Override
    public EquipmentType findById(String id) {
        return entityManager.createQuery(
                        "SELECT e FROM EquipmentType e WHERE e.id = :id AND e.deleted = false", EquipmentType.class)
                .setParameter("id", id)
                .getSingleResult();
    }


}
