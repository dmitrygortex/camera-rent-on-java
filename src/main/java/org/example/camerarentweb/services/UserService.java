package org.example.camerarentweb.services;

import org.example.camerarentweb.entities.User;
import org.example.camerarentweb.entities.UserRole;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User createUser(User user);

    boolean isPhoneNumberUnique(String phoneNumber);
    boolean isEmailUnique(String email);

    Optional<User> getUserById(Long id);
    Optional<User> getUserByPhoneNumber(String phoneNumber);
    Optional<User> getUserByEmail(String email);
    List<User> getAllUsers();
    List<User> getUsersByRole(UserRole role);

    User updateUser(User user);
    void updatePassword(Long userId, String oldPassword, String newPassword);
    void updateEmail(Long userId, String newEmail);
    void updatePhoneNumber(Long userId, String newPhoneNumber);
    void updateUserRole(Long userId, UserRole newRole);

    void deleteUser(Long userId);

    boolean authenticateUser(String phoneNumberOrEmail, String password);
    void logoutUser(Long userId);

    List<User> searchUserByRole(UserRole user);
    List<User> getUsersByLastName(String lastName);

    long countUsers();
    long countUsersByRole(UserRole role);

    void activateUser(Long userId);
    void deactivateUser(Long userId);
    boolean isUserActive(Long userId);
}
