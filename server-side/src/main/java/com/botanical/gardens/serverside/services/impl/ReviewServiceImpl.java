package com.botanical.gardens.serverside.services.impl;

import com.botanical.gardens.serverside.entities.Review;
import com.botanical.gardens.serverside.services.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

// TODO: Implement features
@Service
public class ReviewServiceImpl implements ReviewService {
    @Override
    public void addReviewToAttraction(Long attractionId, Review comment) {

    }

    @Override
    public void addReviewToTour(Long tourId, Review comment) {

    }

    @Override
    public List<Review> fetchReviewsByAttraction(Long attractionId) {
        return null;
    }

    @Override
    public List<Review> fetchReviewsByTour(Long tourId) {
        return null;
    }

    @Override
    public List<Review> fetchReviewsByUser(Long userId) {
        return null;
    }
}
