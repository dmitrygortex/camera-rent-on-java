package org.example.camerarentweb.entities;

import java.io.Serializable;
import java.util.Objects;

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
        // мб добавить проверку на null, подумать над этим
        if (!(o instanceof OrderEquipmentUnitId)) return false;
        OrderEquipmentUnitId that = (OrderEquipmentUnitId) o;
        return orderId.equals(that.orderId) && equipmentUnitId.equals(that.equipmentUnitId);
    }

    @Override
    public int hashCode() {
        //почитать какое хэширование лучшая практика
        //return orderId.hashCode() + equipmentUnitId.hashCode();
        return Objects.hash(orderId, equipmentUnitId);
    }
}


