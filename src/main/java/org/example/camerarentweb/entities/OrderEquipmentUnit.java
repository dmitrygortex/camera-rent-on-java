package org.example.camerarentweb.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "order_equipment")
public class OrderEquipmentUnit{

    @EmbeddedId
    private OrderEquipmentUnitId id;

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "order_id")
    //@JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;

    @ManyToOne
    @MapsId("equipmentUnitId")
    @JoinColumn(name = "equipment_unit_id")
    //@JoinColumn(name = "equipment_unit_id", referencedColumnName = "id")
    private EquipmentUnit equipmentUnit;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    public OrderEquipmentUnitId getId() {
        return id;
    }

    public void setId(OrderEquipmentUnitId id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public EquipmentUnit getEquipmentUnit() {
        return equipmentUnit;
    }

    public void setEquipmentUnit(EquipmentUnit equipmentUnit) {
        this.equipmentUnit = equipmentUnit;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

