package org.example.camerarentweb.entities;

public enum OrderStatus {

    // Заказ создан
    CREATED(0),

    // Заказ подтвержден
    CONFIRMED(1),

    // Заказ обрабатывается
    PROCESSING(2),

    // Заказ завершен
    COMPLETED(3),

    // Заказ отменен
    CANCELLED(4);

    private final int value;

    OrderStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
