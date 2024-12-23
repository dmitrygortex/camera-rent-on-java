package org.example.camerarentweb.entities;

public enum UserRole {

    USER(0),

    ADMIN(1);

    private final int value;

    UserRole(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    public String getAuthority() {
        return "ROLE_" + this.name();
    }
}
