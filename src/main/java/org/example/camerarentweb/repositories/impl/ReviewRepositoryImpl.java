package org.example.camerarentweb.repositories.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.example.camerarentweb.entities.Order;
import org.example.camerarentweb.entities.Review;
import org.example.camerarentweb.repositories.ReviewRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReviewRepositoryImpl extends BaseRepositoryImpl<Review> implements ReviewRepository {

//    @PersistenceContext
//    private EntityManager entityManager;

    public ReviewRepositoryImpl() {
        super(Review.class);
    }

    @Override
    public List<Review> findByUserId(String userId) {
        return entityManager.createQuery(
                        "SELECT r FROM Review r WHERE r.user.id = :userId AND r.deleted = false", Review.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @Override
    public List<Review> findByEquipmentTypeId(int equipmentTypeId) {
        System.out.println("equipmentTypeId in ReviewRepositoryImpl.findByEquipmentTypeId: " + equipmentTypeId);
        return entityManager.createQuery(
                        "SELECT r FROM Review r WHERE r.equipmentType.id = :equipmentTypeId AND r.deleted = false", Review.class)
                .setParameter("equipmentTypeId", equipmentTypeId)
                .getResultList();
    }
}