package org.example.camerarentweb.repositories.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.example.camerarentweb.entities.Category;
import org.example.camerarentweb.entities.EquipmentType;
import org.example.camerarentweb.repositories.CategoryRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryRepositoryImpl extends BaseRepositoryImpl<Category> implements CategoryRepository {
//        @PersistenceContext
//        private EntityManager entityManager;

    public CategoryRepositoryImpl() {
        super(Category.class);
    }

    @Override
    public Category categoryByName(String name) {
        return entityManager.createQuery(
                        "SELECT c FROM Category c WHERE c.name = :name AND c.deleted = false", Category.class)
                .setParameter("name", name)
                .getSingleResult();
    }
}
