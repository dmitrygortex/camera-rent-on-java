package org.example.camerarentweb.repositories.impl;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.example.camerarentweb.entities.BaseEntity;
import org.example.camerarentweb.repositories.BaseRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public class BaseRepositoryImpl<T extends BaseEntity> implements BaseRepository<T> {

    @PersistenceContext
    protected EntityManager entityManager;

    private final Class<T> entityClass;

    protected BaseRepositoryImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Transactional
    public T save(T entity) {
        if (entity.getId() == 0) {
            entityManager.persist(entity);
        } else {
            entity = entityManager.merge(entity);
        }
        return entity;
    }


    public Optional<T> findById(int id) {
        T entity = entityManager.find(entityClass, id);
        return entity != null && !entity.isDeleted() ? Optional.of(entity) : Optional.empty();
    }


    @Transactional
    public void softDeleteById(int id) {
        Optional<T> optionalEntity = findById(id);
        if (optionalEntity.isPresent()) {
            T entity = optionalEntity.get();
            entity.setDeleted(true);
            entityManager.merge(entity);
        }
    }


    @Override
    public List<T> findAll(int page, int size) {
        return entityManager.createQuery(
                "SELECT e FROM " + entityClass.getSimpleName() + " e WHERE e.deleted = false",
                        entityClass)
                .setFirstResult(page * size)
                .setMaxResults(size)
                .getResultList();
    }
}
