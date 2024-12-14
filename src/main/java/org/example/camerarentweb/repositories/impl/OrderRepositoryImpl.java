package org.example.camerarentweb.repositories.impl;

import jakarta.persistence.TypedQuery;
import org.example.camerarentweb.entities.*;
import org.example.camerarentweb.repositories.OrderRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class OrderRepositoryImpl extends BaseRepositoryImpl<Order> implements OrderRepository {

    public OrderRepositoryImpl() {
        super(Order.class);
    }

    @Override
    public List<Order> findByStatus(OrderStatus status) {
        return entityManager.createQuery(
                        "SELECT o FROM Order o WHERE o.status = :status AND o.deleted = false", Order.class)
                .setParameter("status", status)
                .getResultList();
    }

    @Override
    public List<Order> findByUser(User user) {
        return entityManager.createQuery(
                        "SELECT o FROM Order o WHERE o.user = :user AND o.deleted = false", Order.class)
                .setParameter("user", user)
                .getResultList();
    }

    @Override
    public List<Order> findByStatusAndUser(OrderStatus status, User user) {
        return entityManager.createQuery(
                        "SELECT o FROM Order o WHERE o.status = :status AND o.user = :user AND o.deleted = false", Order.class)
                .setParameter("status", status)
                .setParameter("user", user)
                .getResultList();
    }

    @Override
    public List<Order> findByStartDateBetween(LocalDateTime startDate, LocalDateTime endDate) {
        return entityManager.createQuery(
                        "SELECT o FROM Order o WHERE o.startDate BETWEEN :startDate AND :endDate AND o.deleted = false", Order.class)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate)
                .getResultList();
    }

    @Override
    public List<Order> findByUserAndStartDateBetween(User user, LocalDateTime startDate, LocalDateTime endDate) {
        return entityManager.createQuery(
                        "SELECT o FROM Order o WHERE o.user = :user AND o.startDate BETWEEN :startDate AND :endDate AND o.deleted = false", Order.class)
                .setParameter("user", user)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate)
                .getResultList();
    }

    @Override
    public List<Order> findByStatusAndStartDateBetween(OrderStatus status, LocalDateTime startDate, LocalDateTime endDate) {
        return entityManager.createQuery(
                        "SELECT o FROM Order o WHERE o.status = :status AND o.startDate BETWEEN :startDate AND :endDate AND o.deleted = false", Order.class)
                .setParameter("status", status)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate)
                .getResultList();
    }

    @Override
    public List<Order> findByEquipmentTypeName(EquipmentType equipmentType) {
        return entityManager.createQuery(
                        "SELECT o FROM Order o JOIN o.equipmentUnits eu WHERE eu.equipmentType = :equipmentType AND o.deleted = false", Order.class)
                .setParameter("equipmentType", equipmentType)
                .getResultList();
    }

    @Override
    public Double findTotalCostByUser(User user) {
        return entityManager.createQuery(
                        "SELECT SUM(o.totalCost) FROM Order o WHERE o.user = :user AND o.deleted = false", Double.class)
                .setParameter("user", user)
                .getSingleResult();
    }

    @Override
    public Long countByStatus(OrderStatus status) {
        return entityManager.createQuery(
                        "SELECT COUNT(o) FROM Order o WHERE o.status = :status AND o.deleted = false", Long.class)
                .setParameter("status", status)
                .getSingleResult();
    }

    @Override
    public List<OrderEquipmentUnit> findOrderEquipmentUnitsByOrder(Order order) {
        return entityManager.createQuery(
                        "SELECT oe FROM OrderEquipmentUnit oe WHERE oe.order = :order", OrderEquipmentUnit.class)
                .setParameter("order", order)
                .getResultList();
    }

    @Override
    public List<OrderEquipmentUnit> findOrderEquipmentUnitsByEquipmentUnit(EquipmentUnit equipmentUnit) {
        return entityManager.createQuery(
                        "SELECT oe FROM OrderEquipmentUnit oe WHERE oe.equipmentUnit = :equipmentUnit", OrderEquipmentUnit.class)
                .setParameter("equipmentUnit", equipmentUnit)
                .getResultList();
    }

    @Override
    public OrderEquipmentUnit findOrderEquipmentUnitByOrderAndEquipmentUnit(Order order, EquipmentUnit equipmentUnit) {
        return entityManager.createQuery(
                        "SELECT oe FROM OrderEquipmentUnit oe WHERE oe.order = :order AND oe.equipmentUnit = :equipmentUnit", OrderEquipmentUnit.class)
                .setParameter("order", order)
                .setParameter("equipmentUnit", equipmentUnit)
                .getSingleResult();
    }

    @Override
    public List<Order> findOrderByEquipmentUnit(EquipmentUnit equipmentUnit) {
        return entityManager.createQuery(
                        "SELECT o FROM Order o JOIN o.orderEquipmentUnits eu WHERE eu = :equipmentUnit AND o.deleted = false", Order.class)
                .setParameter("equipmentUnit", equipmentUnit)
                .getResultList();
    }
}
