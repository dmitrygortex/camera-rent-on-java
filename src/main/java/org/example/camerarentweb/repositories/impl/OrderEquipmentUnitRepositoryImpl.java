package org.example.camerarentweb.repositories.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.camerarentweb.entities.OrderEquipmentUnit;
import org.example.camerarentweb.repositories.OrderEquipmentUnitRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class OrderEquipmentUnitRepositoryImpl implements OrderEquipmentUnitRepository {

    @PersistenceContext
    protected EntityManager entityManager;

    private final Class<OrderEquipmentUnit> entityClass;

    protected OrderEquipmentUnitRepositoryImpl() {
        this.entityClass = OrderEquipmentUnit.class;
    }

    @Transactional
    public OrderEquipmentUnit save(OrderEquipmentUnit entity) {
        System.out.println("Saving entity: " + entity);
        System.out.println("Entity ID: " + entity.getId());
        if (entity.getId() == null || entity.getId().equals(0)) {
            entityManager.persist(entity);
        } else {
            entity = entityManager.merge(entity);
        }
        return entity;
    }


    public Optional<OrderEquipmentUnit> findById(int id) {
        OrderEquipmentUnit entity = entityManager.find(entityClass, id);
        return entity != null ? Optional.of(entity) : Optional.empty();
    }


    @Override
    public List<OrderEquipmentUnit> findAll() {
        //int page, int size
        return entityManager.createQuery(
                        "SELECT e FROM " + entityClass.getSimpleName() + " e",
                        entityClass)
               // .setFirstResult(page * size)
                //.setMaxResults(size)
                .getResultList();
    }
}
