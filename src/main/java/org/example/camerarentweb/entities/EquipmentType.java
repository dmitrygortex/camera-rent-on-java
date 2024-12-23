package org.example.camerarentweb.entities;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "equipment_type")
public class EquipmentType extends BaseEntity {

    private String name;
    private String description;
    private double price;
    private Category category;
    private String photo;

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    @Column(name = "price", nullable = false)
    public double getPrice() {
        return price;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    public Category getCategory() {
        return category;
    }

    @Column(name = "photo")
    public String getPhoto() {
        return photo;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or blank");
        }
        if (name.length() > 100) {
            throw new IllegalArgumentException("Name cannot be longer than 100 characters");
        }
        this.name = name.trim();
    }

    public void setDescription(String description) {
        if (description != null && description.length() > 1000) {
            throw new IllegalArgumentException("Description cannot be longer than 1000 characters");
        }
        this.description = description;
    }

    public void setPrice(double price) {
        if (price < 0.00) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        if (price > 1000000.00) {
            throw new IllegalArgumentException("Price cannot exceed 1,000,000.00");
        }
        this.price = Math.round(price * 100.0) / 100.0; // Round to 2 decimal places
    }

    public void setCategory(Category category) {
        if (category == null) {
            throw new IllegalArgumentException("Category cannot be null");
        }
        this.category = category;
    }

    public void setPhoto(String photo) {
        if (photo != null && photo.length() > 255) {
            throw new IllegalArgumentException("Photo URL cannot be longer than 255 characters");
        }
        this.photo = photo;
    }
}

