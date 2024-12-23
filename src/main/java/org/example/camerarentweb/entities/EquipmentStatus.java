package org.example.camerarentweb.entities;

// Статус оборудования:
// 0 - доступно,
// 1 - забронировано,
// 2 - арендовано,
// 3 - в ремонте,
// 4 - недоступно,
// 5 - в доставке,
// 6 - выведено из эксплуатации
public enum EquipmentStatus {

    //доступно
    AVAILABLE(0),

    //забронировано
    RESERVED(1),

    //арендовано
    RENTED_OUT(2),

    //в ремонте
    ON_MAINTENANCE(3),

    //недоступно
    UNAVAILABLE(4),

    //в доставке
    IN_DELIVERY(5),

    //выведено из эксплуатации
    DECOMMISSIONED(6);

    private final int value;

    EquipmentStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}


