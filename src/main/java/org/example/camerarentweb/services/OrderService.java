package org.example.camerarentweb.services;

import org.example.camerarentweb.entities.Order;
import org.example.camerarentweb.entities.User;
import org.example.camerarentweb.entities.EquipmentUnit;
import org.example.camerarentweb.entities.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderService {

    Order createOrder(User user, List<EquipmentUnit> equipmentUnits, LocalDateTime startDate, LocalDateTime endDate);

    Order updateOrder(int orderId, List<EquipmentUnit> equipmentUnits, LocalDateTime startDate, LocalDateTime endDate);

    void cancelOrder(int orderId);

    Order findOrderById(int orderId);

    List<Order> findOrdersByUser(User user);

    List<Order> findOrdersByStatus(OrderStatus status);

    List<Order> findOrdersWithinDateRange(LocalDateTime startDate, LocalDateTime endDate);

    double calculateOrderCost(Order order);

    void changeOrderStatus(int orderId, OrderStatus newStatus);
}
