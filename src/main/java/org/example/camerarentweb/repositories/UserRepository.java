package org.example.camerarentweb.repositories;

import org.example.camerarentweb.entities.User;
import org.example.camerarentweb.entities.UserRole;

import java.util.List;

public interface UserRepository extends BaseRepository<User> {

    User findByPhoneNumber(String phoneNumber);

    User findByEmail(String email);

    List<User> findByLastName(String lastName);

    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber);

    long countUsers();

    List<User> findByRole(UserRole role);

    User findByEmailAndPassword(String email, String password);

    void activateUser(Long id);

    void deactivateUser(Long id);
}
