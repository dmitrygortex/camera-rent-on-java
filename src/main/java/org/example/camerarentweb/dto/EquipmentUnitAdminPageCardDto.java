package org.example.camerarentweb.dto;

public record EquipmentUnitAdminPageCardDto(
        int id,
        String serialNumber,
        String name,
        String status,
        // скорее всего в ближайшем будующем понадобятся заметки поэтому пусть будут если что уберу позже
        String notes
)
{
}
