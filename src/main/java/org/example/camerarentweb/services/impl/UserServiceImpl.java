package org.example.camerarentweb.services.impl;

import org.example.camerarentweb.entities.User;
import org.example.camerarentweb.entities.UserRole;
import org.example.camerarentweb.repositories.UserRepository;
import org.example.camerarentweb.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public boolean isPhoneNumberUnique(String phoneNumber) {
        return !userRepository.existsByPhoneNumber(phoneNumber);
    }

    @Override
    public boolean isEmailUnique(String email) {
        return !userRepository.existsByEmail(email);
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id.intValue());
    }

    @Override
    public Optional<User> getUserByPhoneNumber(String phoneNumber) {
        return Optional.ofNullable(userRepository.findByPhoneNumber(phoneNumber));
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return Optional.ofNullable(userRepository.findByEmail(email));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll(0, Integer.MAX_VALUE);
    }

    @Override
    public List<User> getUsersByRole(UserRole role) {
        return userRepository.findByRole(role);
    }

    @Override
    @Transactional
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public void updatePassword(Long userId, String oldPassword, String newPassword) {
        Optional<User> userOpt = getUserById(userId);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (user.getPassword().equals(oldPassword)) {
                user.setPassword(newPassword);
                userRepository.save(user);
            } else {
                throw new IllegalArgumentException("Old password is incorrect");
            }
        } else {
            throw new IllegalArgumentException("User not found");
        }
    }

    @Override
    @Transactional
    public void updateEmail(Long userId, String newEmail) {
        Optional<User> userOpt = getUserById(userId);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setEmail(newEmail);
            userRepository.save(user);
        } else {
            throw new IllegalArgumentException("User not found");
        }
    }

    @Override
    @Transactional
    public void updatePhoneNumber(Long userId, String newPhoneNumber) {
        Optional<User> userOpt = getUserById(userId);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setPhoneNumber(newPhoneNumber);
            userRepository.save(user);
        } else {
            throw new IllegalArgumentException("User not found");
        }
    }

    @Override
    @Transactional
    public void updateUserRole(Long userId, UserRole newRole) {
        Optional<User> userOpt = getUserById(userId);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setRole(newRole);
            userRepository.save(user);
        } else {
            throw new IllegalArgumentException("User not found");
        }
    }

    @Override
    @Transactional
    public void deleteUser(Long userId) {
        userRepository.softDeleteById(userId.intValue());
    }

    @Override
    public boolean authenticateUser(String phoneNumberOrEmail, String password) {
        User user = userRepository.findByEmail(phoneNumberOrEmail);
        if (user == null) {
            user = userRepository.findByPhoneNumber(phoneNumberOrEmail);
        }
        return user != null && user.getPassword().equals(password);
    }

    @Override
    public void logoutUser(Long userId) {
        // i will make it after lesson with authorization theory
    }

    @Override
    public List<User> searchUserByRole(UserRole role) {
        return userRepository.findByRole(role);
    }

    @Override
    public List<User> getUsersByLastName(String lastName) {
        return userRepository.findByLastName(lastName);
    }

    @Override
    public long countUsers() {
        return userRepository.countUsers();
    }

    @Override
    public long countUsersByRole(UserRole role) {
        return userRepository.findByRole(role).size();
    }

    @Override
    @Transactional
    public void activateUser(Long userId) {
        userRepository.activateUser(userId);
    }

    @Override
    @Transactional
    public void deactivateUser(Long userId) {
        userRepository.deactivateUser(userId);
    }

    @Override
    public boolean isUserActive(Long userId) {
        Optional<User> userOpt = getUserById(userId);
        return userOpt.map(user -> !user.isDeleted()).orElse(false);
    }
}