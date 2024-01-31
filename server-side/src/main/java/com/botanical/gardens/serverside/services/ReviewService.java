package com.botanical.gardens.serverside.services;

import com.botanical.gardens.serverside.entities.Comment;
import com.botanical.gardens.serverside.entities.Review;

import java.util.List;

public interface ReviewService {
    void addReviewToAttraction(Long attractionId, Review comment);
    void addReviewToTour(Long tourId, Review comment);
    List<Review> fetchReviewsByAttraction(Long attractionId);
    List<Review> fetchReviewsByTour(Long tourId);
    List<Review> fetchReviewsByUser(Long userId);
}
