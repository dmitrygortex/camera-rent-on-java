package org.example.camerarentweb.services;

import org.example.camerarentweb.entities.User;
import org.example.camerarentweb.entities.UserRole;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService{

    User createUser(User user);

    boolean isPhoneNumberUnique(String phoneNumber);
    boolean isEmailUnique(String email);

    Optional<User> getUserById(int id);
    Optional<User> getUserByPhoneNumber(String phoneNumber);
    Optional<User> getUserByEmail(String email);
    List<User> getAllUsers();
    List<User> getUsersByRole(UserRole role);

    User updateUser(User user);
    void updatePassword(int userId, String oldPassword, String newPassword);
    void updateEmail(int userId, String newEmail);
    void updatePhoneNumber(int userId, String newPhoneNumber);
    void updateUserRole(int userId, UserRole newRole);

    void deleteUser(int userId);

    boolean authenticateUser(String phoneNumberOrEmail, String password);
    void logoutUser(int userId);

    List<User> searchUserByRole(UserRole user);
    List<User> getUsersByLastName(String lastName);

    long countUsers();
    long countUsersByRole(UserRole role);

    void activateUser(int userId);
    void deactivateUser(int userId);
    boolean isUserActive(int userId);
}
