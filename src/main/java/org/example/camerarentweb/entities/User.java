package org.example.camerarentweb.entities;

import jakarta.persistence.*;
import java.util.regex.Pattern;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

    private String phoneNumber;
    private String password;
    private String email;
    private String lastName;
    private String firstName;
    private UserRole role;

    private static final Pattern PHONE_PATTERN = Pattern.compile("^\\+?[0-9]{10,14}$");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");

    public User() {}

    public User(String phoneNumber, String password, String email, String lastName, String firstName, UserRole role) {
        setPhoneNumber(phoneNumber);
        setPassword(password);
        setEmail(email);
        setLastName(lastName);
        setFirstName(firstName);
        setRole(role);
    }

    @Column(name = "phone_number", nullable = false)
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Column(name = "password", nullable = false)
    public String getPassword() {
        return password;
    }

    @Column(name = "email", nullable = false)
    public String getEmail() {
        return email;
    }

    @Column(name = "last_name", nullable = false)
    public String getLastName() {
        return lastName;
    }

    @Column(name = "first_name", nullable = false)
    public String getFirstName() {
        return firstName;
    }

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "role", nullable = false)
    public UserRole getRole() {
        return role;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Phone number cannot be null or blank");
        }
        if (!PHONE_PATTERN.matcher(phoneNumber).matches()) {
            throw new IllegalArgumentException("Invalid phone number format");
        }
        this.phoneNumber = phoneNumber.trim();
    }

    public void setPassword(String password) {
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or blank");
        }
        if (password.length() < 8 || password.length() > 64) {
            throw new IllegalArgumentException("Password must be between 8 and 64 characters");
        }
        this.password = password;
    }

    public void setEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or blank");
        }
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new IllegalArgumentException("Invalid email format");
        }
        this.email = email.trim().toLowerCase();
    }

    public void setLastName(String lastName) {
        if (lastName == null || lastName.trim().isEmpty()) {
            throw new IllegalArgumentException("Last name cannot be null or blank");
        }
        if (lastName.length() > 50) {
            throw new IllegalArgumentException("Last name cannot be longer than 50 characters");
        }
        this.lastName = lastName.trim();
    }

    public void setFirstName(String firstName) {
        if (firstName == null || firstName.trim().isEmpty()) {
            throw new IllegalArgumentException("First name cannot be null or blank");
        }
        if (firstName.length() > 50) {
            throw new IllegalArgumentException("First name cannot be longer than 50 characters");
        }
        this.firstName = firstName.trim();
    }

    public void setRole(UserRole role) {
        if (role == null) {
            throw new IllegalArgumentException("User role cannot be null");
        }
        this.role = role;
    }
}
