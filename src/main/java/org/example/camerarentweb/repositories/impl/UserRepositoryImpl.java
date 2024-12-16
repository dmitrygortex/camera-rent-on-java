package org.example.camerarentweb.repositories.impl;

import jakarta.persistence.TypedQuery;
import org.example.camerarentweb.entities.User;
import org.example.camerarentweb.entities.UserRole;
import org.example.camerarentweb.repositories.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImpl extends BaseRepositoryImpl<User> implements UserRepository {

    public UserRepositoryImpl() {
        super(User.class);
    }

    @Override
    public User findByPhoneNumber(String phoneNumber) {
        return entityManager.createQuery(
                        "SELECT u FROM User u WHERE u.phoneNumber = :phoneNumber AND u.deleted = false", User.class)
                .setParameter("phoneNumber", phoneNumber)
                .getSingleResult();
    }

    @Override
    public User findByEmail(String email) {
        return entityManager.createQuery(
                        "SELECT u FROM User u WHERE u.email = :email AND u.deleted = false", User.class)
                .setParameter("email", email)
                .getSingleResult();
    }

    @Override
    public List<User> findByLastName(String lastName) {
        return entityManager.createQuery(
                        "SELECT u FROM User u WHERE u.lastName = :lastName AND u.deleted = false", User.class)
                .setParameter("lastName", lastName)
                .getResultList();
    }

    @Override
    public boolean existsByEmail(String email) {
        Long count = entityManager.createQuery(
                        "SELECT COUNT(u) FROM User u WHERE u.email = :email AND u.deleted = false", Long.class)
                .setParameter("email", email)
                .getSingleResult();
        return count > 0;
    }

    @Override
    public boolean existsByPhoneNumber(String phoneNumber) {
        Long count = entityManager.createQuery(
                        "SELECT COUNT(u) FROM User u WHERE u.phoneNumber = :phoneNumber AND u.deleted = false", Long.class)
                .setParameter("phoneNumber", phoneNumber)
                .getSingleResult();
        return count > 0;
    }

    @Override
    public long countUsers() {
        return entityManager.createQuery(
                        "SELECT COUNT(u) FROM User u WHERE u.deleted = false", Long.class)
                .getSingleResult();
    }

    @Override
    public List<User> findByRole(UserRole role) {
        return entityManager.createQuery(
                        "SELECT u FROM User u WHERE u.role = :role AND u.deleted = false", User.class)
                .setParameter("role", role)
                .getResultList();
    }

    @Override
    public User findByEmailAndPassword(String email, String password) {
        return entityManager.createQuery(
                        "SELECT u FROM User u WHERE u.email = :email AND u.password = :password AND u.deleted = false", User.class)
                .setParameter("email", email)
                .setParameter("password", password)
                .getSingleResult();
    }

    @Override
    public void activateUser(int id) {
        User user = entityManager.find(User.class, id);
        if (user != null && user.isDeleted()) {
            user.setDeleted(false);
            entityManager.merge(user);
        }
    }

    @Override
    public void deactivateUser(int id) {
        User user = entityManager.find(User.class, id);
        if (user != null && !user.isDeleted()) {
            user.setDeleted(true);
            entityManager.merge(user);
        }
    }
}