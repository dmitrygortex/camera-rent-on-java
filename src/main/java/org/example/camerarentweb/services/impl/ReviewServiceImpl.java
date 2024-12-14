package org.example.camerarentweb.services.impl;

import org.example.camerarentweb.dto.ReviewDto;
import org.example.camerarentweb.entities.Review;
import org.example.camerarentweb.repositories.ReviewRepository;
import org.example.camerarentweb.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public double calculateEquipmentTypeRating(int equipmentTypeId) {
        List<Review> reviews = reviewRepository.findByEquipmentTypeId(equipmentTypeId);
        if (reviews.isEmpty()) {
            return 0.0;
        }
        double totalRating = reviews.stream()
                .mapToInt(Review::getRate)
                .sum();
        double averageRating = totalRating / reviews.size();

        return Math.round(averageRating * 10.0) / 10.0;
    }

    @Override
    public List<ReviewDto> findByEquipmentTypeId(int equipmentTypeId) {
        List<Review> reviewList = reviewRepository.findByEquipmentTypeId(equipmentTypeId);
        System.out.println("reviewList size in service: " + String.valueOf(reviewList.size()));
        return reviewList.stream()
                .map(this::mapToReviewDto)
                .collect(Collectors.toList());
    }

    private ReviewDto mapToReviewDto(Review review) {
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setHeader(review.getHeader());
        reviewDto.setAdvantages(review.getAdvantages());
        reviewDto.setDisadvantages(review.getDisadvantages());
        reviewDto.setCommentary(review.getCommentary());
        reviewDto.setRate(review.getRate());
        reviewDto.setUser(review.getUser());
        reviewDto.setEquipmentType(review.getEquipmentType());
        return reviewDto;
    }
}
