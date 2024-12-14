package org.example.camerarentweb.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "equipment_unit")
public class EquipmentUnit extends BaseEntity {

    private EquipmentType equipmentType;
    private String serialNumber;
    private EquipmentStatus status;
    private String notes;

    @OneToMany(mappedBy = "equipmentUnit", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderEquipmentUnit> orderEquipmentUnits;


    @ManyToOne(optional = false)
    @JoinColumn(name = "equipment_type_id", referencedColumnName = "id")
    public EquipmentType getEquipmentType() {
        return equipmentType;
    }

    @Column(name = "serial_number", nullable = false, unique = true)
    public String getSerialNumber() {
        return serialNumber;
    }

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status", nullable = false)
    public EquipmentStatus getStatus() {
        return status;
    }

    @Column(name = "notes", length = 1000)
    public String getNotes() {
        return notes;
    }

    public void setEquipmentType(EquipmentType equipmentType) {
        if (equipmentType == null) {
            throw new IllegalArgumentException("Equipment type cannot be null");
        }
        this.equipmentType = equipmentType;
    }

    public void setSerialNumber(String serialNumber) {
        if (serialNumber == null || serialNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Serial number cannot be null or blank");
        }
        if (serialNumber.length() > 50) {
            throw new IllegalArgumentException("Serial number cannot be longer than 50 characters");
        }
        this.serialNumber = serialNumber.trim();
    }

    public void setStatus(EquipmentStatus status) {
        if (status == null) {
            throw new IllegalArgumentException("Equipment status cannot be null");
        }
        this.status = status;
    }

    public void setNotes(String notes) {
        if (notes != null && notes.length() > 1000) {
            throw new IllegalArgumentException("Notes cannot be longer than 1000 characters");
        }
        this.notes = notes;
    }
}