package org.example.camerarentweb.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "category")
public class Category extends BaseEntity {

    private Category parentCategory;
    private String name;
    private String parameters;

    @ManyToOne
    @JoinColumn(name = "parent_category_id", referencedColumnName = "id", nullable = true)
    public Category getParentCategory() {
        return parentCategory;
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    @Column(name = "parameters", nullable = true)
    public String getParameters() {
        return parameters;
    }

    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setParameters(String parameters) {
        this.parameters = parameters;
    }
}
