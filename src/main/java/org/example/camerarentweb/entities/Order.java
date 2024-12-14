package org.example.camerarentweb.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class Order extends BaseEntity {

    //private EquipmentUnit equipmentUnits;
    private int amount;
    private OrderStatus status;
    private User user;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private double cost;
    //private int equipmentId;

//    @OneToMany(mappedBy = "equipment_unit_id")
//    public List<EquipmentUnit> getEquipmentUnits() {
//        return equipmentUnits;
//    }
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderEquipmentUnit> orderEquipmentUnits;

//    @Column(name = "equipment_id", nullable = false)
//    public int getEquipmentId() {
//        return equipmentId;
//    }


    @Column(name = "amount", nullable = false)
    @Min(1)
    public int getAmount() {
        return amount;
    }

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status", nullable = false)
    public OrderStatus getStatus() {
        return status;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public User getUser() {
        return user;
    }

    @Column(name = "start_date", nullable = false)
    public LocalDateTime getStartDate() {
        return startDate;
    }

    @Column(name = "end_date", nullable = false)
    public LocalDateTime getEndDate() {
        return endDate;
    }

    @Column(name = "cost", nullable = false)
    public double getCost() {
        return cost;
    }

//    public void setEquipmentUnit(List<EquipmentUnit> equipmentUnits) {
//        this.equipmentUnits = equipmentUnits;
//    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
