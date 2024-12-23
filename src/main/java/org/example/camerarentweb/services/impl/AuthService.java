package org.example.camerarentweb.services.impl;

import org.example.camerarentcontracts.input.UserLoginInputModel;
import org.example.camerarentcontracts.input.UserRegisterInputModel;
import org.example.camerarentweb.entities.User;
import org.example.camerarentweb.entities.UserRole;
import org.example.camerarentweb.repositories.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User authenticate(UserLoginInputModel loginInputModel) {

        var user = userRepository.findByEmail(loginInputModel.getUsername());

        System.out.println("User from DB: " + loginInputModel.getUsername());
        System.out.println("Password matches: " + passwordEncoder.matches(loginInputModel.getPassword(), user.getPassword()));

        if (!passwordEncoder.matches(loginInputModel.getPassword(), user.getPassword())) {
            throw new UsernameNotFoundException("Invalid credentials");
        }

        return user;
    }

    public void register(UserRegisterInputModel userRegisterInputModel) {
        System.out.println("register user method" + userRegisterInputModel);
        if (!userRegisterInputModel.getPassword().equals(userRegisterInputModel.getConfirmPassword())) {
            throw new RuntimeException("passwords.match");
        }

        Optional<User> byEmail = this.userRepository.findOptionalByEmail(userRegisterInputModel.getEmail());

        if (byEmail.isPresent()) {
            throw new RuntimeException("email.used");
        }

        var userRole = UserRole.USER;

        User user = new User(
                userRegisterInputModel.getPhoneNumber(),
                passwordEncoder.encode(userRegisterInputModel.getPassword()),
                userRegisterInputModel.getEmail(),
                userRegisterInputModel.getLastName(),
                userRegisterInputModel.getFirstName(),
                userRole
        );

        this.userRepository.save(user);
    }
}



