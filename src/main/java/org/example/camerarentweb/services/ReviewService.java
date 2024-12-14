package org.example.camerarentweb.services;

import org.example.camerarentweb.dto.ReviewDto;

import java.util.List;

public interface ReviewService {
    double calculateEquipmentTypeRating(int equipmentTypeId);
    List<ReviewDto> findByEquipmentTypeId(int equipmentTypeId);
}
