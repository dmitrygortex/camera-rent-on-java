package org.example.camerarentweb.repositories;

import org.example.camerarentweb.entities.*;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends BaseRepository<Order> {

    List<Order> findByStatus(OrderStatus status);

    List<Order> findByUser(User user);

    List<Order> findByStatusAndUser(OrderStatus status, User user);

    List<Order> findByStartDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    List<Order> findByUserAndStartDateBetween(User user, LocalDateTime startDate, LocalDateTime endDate);

    List<Order> findByStatusAndStartDateBetween(OrderStatus status, LocalDateTime startDate, LocalDateTime endDate);

    List<Order> findByEquipmentTypeName(EquipmentType equipmentType);

    Double findTotalCostByUser(User user);

    Long countByStatus(OrderStatus status);

    List<OrderEquipmentUnit> findOrderEquipmentUnitsByOrder(Order order);

    List<OrderEquipmentUnit> findOrderEquipmentUnitsByEquipmentUnit(EquipmentUnit equipmentUnit);

    OrderEquipmentUnit findOrderEquipmentUnitByOrderAndEquipmentUnit(Order order, EquipmentUnit equipmentUnit);

    List<Order> findOrderByEquipmentUnit(EquipmentUnit equipmentUnit);

}