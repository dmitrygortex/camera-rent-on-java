package org.example.camerarentweb.entities;

import java.io.Serializable;
import jakarta.persistence.Embeddable;

@Embeddable
public class OrderEquipmentUnitId implements Serializable {
    private Integer orderId;
    private Integer equipmentUnitId;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getEquipmentUnitId() {
        return equipmentUnitId;
    }

    public void setEquipmentUnitId(Integer equipmentUnitId) {
        this.equipmentUnitId = equipmentUnitId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderEquipmentUnitId)) return false;
        OrderEquipmentUnitId that = (OrderEquipmentUnitId) o;
        return orderId.equals(that.orderId) && equipmentUnitId.equals(that.equipmentUnitId);
    }

    @Override
    public int hashCode() {
        return orderId.hashCode() + equipmentUnitId.hashCode();
    }
}
