package org.example.camerarentweb.repositories;

import org.example.camerarentweb.dto.ReviewDto;
import org.example.camerarentweb.entities.Review;

import java.util.List;

public interface ReviewRepository extends BaseRepository<Review> {

    List<Review> findByUserId(String userId);

    List<Review> findByEquipmentTypeId(int equipmentUnitId);
}
