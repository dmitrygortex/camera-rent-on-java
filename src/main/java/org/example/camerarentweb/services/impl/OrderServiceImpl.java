package org.example.camerarentweb.services.impl;

import org.example.camerarentweb.entities.*;
import org.example.camerarentweb.repositories.OrderEquipmentUnitRepository;
import org.example.camerarentweb.services.EquipmentUnitService;
import org.example.camerarentweb.services.OrderService;
import org.example.camerarentweb.repositories.impl.OrderRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    // не забыть поменять на нормально добавление зависиомстей через конструктор!!!
    @Autowired
    private OrderRepositoryImpl orderRepository;
    @Autowired
    private EquipmentUnitService equipmentUnitService;
    @Autowired
    private OrderEquipmentUnitRepository orderEquipmentUnitRepository;

    @Override
    @Transactional
    public Order createOrder(User user, List<EquipmentUnit> equipmentUnits, LocalDateTime startDate, LocalDateTime endDate) {
        Order order = new Order();
        order.setUser(user);
        order.setStartDate(startDate);
        order.setEndDate(endDate);
        order.setStatus(OrderStatus.CREATED);
        order.setAmount(equipmentUnits.size());
        orderRepository.save(order);


        for (EquipmentUnit unit : equipmentUnits) {
            OrderEquipmentUnit orderEquipmentUnit = new OrderEquipmentUnit();


            OrderEquipmentUnitId id = new OrderEquipmentUnitId();
            id.setOrderId(order.getId());
            id.setEquipmentUnitId(unit.getId());
            orderEquipmentUnit.setId(id);

            orderEquipmentUnit.setOrder(order);
            orderEquipmentUnit.setEquipmentUnit(unit);
            orderEquipmentUnit.setQuantity(1);

            orderEquipmentUnitRepository.save(orderEquipmentUnit);
        }


        for (EquipmentUnit unit : equipmentUnits) {
            equipmentUnitService.changeUnitStatus(unit.getId(), EquipmentStatus.RESERVED);
        }


        order.setCost(calculateOrderCost(order));
        order.setStatus(OrderStatus.CONFIRMED);


        return orderRepository.save(order);
    }

    @Override
    @Transactional
    public Order updateOrder(int orderId, List<EquipmentUnit> equipmentUnits, LocalDateTime startDate, LocalDateTime endDate) {
//        var tempOrder = new Order();
//        tempOrder.setStartDate(startDate);
//        tempOrder.setEndDate(endDate);

        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (!optionalOrder.isPresent()) {
            throw new IllegalArgumentException("order not exists");
        }

        Order order = optionalOrder.get();
        order.setStartDate(startDate);
        order.setEndDate(endDate);
        order.setAmount(equipmentUnits.size());
        order.setCost(calculateOrderCost(order));

        return orderRepository.save(order);
    }

    @Override
    @Transactional
    public void cancelOrder(int orderId) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (!optionalOrder.isPresent()) {
            throw new IllegalArgumentException("order not exists");
        }

        Order order = optionalOrder.get();
        order.setStatus(OrderStatus.CANCELLED);
        orderRepository.save(order);
    }

    @Override
    public Order findOrderById(int orderId) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        return optionalOrder.orElse(null);
    }

    @Override
    public List<Order> findOrdersByUser(User user) {
        return orderRepository.findByUser(user);
    }

    @Override
    public List<Order> findOrdersByStatus(OrderStatus status) {
        return orderRepository.findByStatus(status);
    }

    @Override
    public List<Order> findOrdersWithinDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return orderRepository.findByStartDateBetween(startDate, endDate);
    }

    @Override
    public double calculateOrderCost(Order order) {
        long days = ChronoUnit.DAYS.between(order.getStartDate(), order.getEndDate());
        List<OrderEquipmentUnit> orderEquipmentUnits = orderRepository.findOrderEquipmentUnitsByOrder(order);
        double cost = 0;
        for (OrderEquipmentUnit orderEquipmentUnit : orderEquipmentUnits){
            var equipment = orderEquipmentUnit.getEquipmentUnit();
            cost += equipment.getEquipmentType().getPrice() * orderEquipmentUnit.getQuantity();
        }
        return cost;
    }

    @Override
    @Transactional
    public void changeOrderStatus(int orderId, OrderStatus newStatus) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (!optionalOrder.isPresent()) {
            throw new IllegalArgumentException("Order not found");
        }

        Order order = optionalOrder.get();
        order.setStatus(newStatus);
        orderRepository.save(order);
    }
}