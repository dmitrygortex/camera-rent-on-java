package org.example.camerarentweb.entities;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "review")
public class Review extends BaseEntity {

    private User user;
    private EquipmentType equipmentType;
    private int rate;
    private String header;
    private String advantages;
    private String disadvantages;
    private String commentary;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public User getUser() {
        return user;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "equipment_type_id", referencedColumnName = "id")
    public EquipmentType getEquipmentType() {
        return equipmentType;
    }

    @Column(name = "rate", nullable = false)
    public int getRate() {
        return rate;
    }

    @Column(name = "header", length = 60)
    public String getHeader() {
        return header;
    }

    @Column(name = "advantages", length = 1500)
    public String getAdvantages() {
        return advantages;
    }

    @Column(name = "disadvantages", length = 1500)
    public String getDisadvantages() {
        return disadvantages;
    }

    @Column(name = "commentary", length = 2000)
    public String getCommentary() {
        return commentary;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setEquipmentType(EquipmentType equipmentType) {
        this.equipmentType = equipmentType;
    }

    public void setRate(int rate) {
        if (rate < 0 || rate > 10) {
            throw new IllegalArgumentException("Rate must be between 0 and 10");
        }
        this.rate = rate;
    }

    public void setHeader(String header) {
        if (header == null || header.length() < 10 || header.length() > 60) {
            throw new IllegalArgumentException("Header must be between 10 and 60 characters");
        }
        this.header = header;
    }

    public void setAdvantages(String advantages) {
        if (advantages != null && advantages.length() > 1500) {
            throw new IllegalArgumentException("Advantages must not exceed 1500 characters");
        }
        this.advantages = advantages;
    }

    public void setDisadvantages(String disadvantages) {
        if (disadvantages != null && disadvantages.length() > 1500) {
            throw new IllegalArgumentException("Disadvantages must not exceed 1500 characters");
        }
        this.disadvantages = disadvantages;
    }

    public void setCommentary(String commentary) {
        if (commentary != null && commentary.length() > 2000) {
            throw new IllegalArgumentException("Commentary must not exceed 2000 characters");
        }
        this.commentary = commentary;
    }
}