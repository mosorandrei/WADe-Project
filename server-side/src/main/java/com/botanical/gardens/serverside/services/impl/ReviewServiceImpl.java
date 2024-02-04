package com.botanical.gardens.serverside.services.impl;

import com.botanical.gardens.serverside.entities.Attraction;
import com.botanical.gardens.serverside.entities.Review;
import com.botanical.gardens.serverside.entities.Tour;
import com.botanical.gardens.serverside.repositories.jpa.AttractionRepository;
import com.botanical.gardens.serverside.repositories.jpa.ReviewRepository;
import com.botanical.gardens.serverside.repositories.jpa.TourRepository;
import com.botanical.gardens.serverside.repositories.jpa.UserRepository;
import com.botanical.gardens.serverside.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ReviewServiceImpl implements ReviewService {

    private final AttractionRepository attractionRepository;

    private final TourRepository tourRepository;

    private final ReviewRepository reviewRepository;

    private final UserRepository userRepository;

    @Autowired
    public ReviewServiceImpl(AttractionRepository attractionRepository, TourRepository tourRepository, ReviewRepository reviewRepository, UserRepository userRepository) {
        this.attractionRepository = attractionRepository;
        this.tourRepository = tourRepository;
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Review saveReview(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public List<Review> fetchReviewsByAttraction(Long attractionId) {
        return attractionRepository.findById(attractionId)
                .map(Attraction::getReviews)
                .orElseThrow(() -> new RuntimeException(String.format("Could not find attraction with the id: %s", attractionId)));
    }

    @Override
    public List<Review> fetchReviewsByTour(Long tourId) {
        return tourRepository.findById(tourId)
                .map(Tour::getReviews)
                .orElseThrow(() -> new RuntimeException(String.format("Could not find tour with the id: %s", tourId)));
    }

    @Override
    public List<Review> fetchReviewsByUser(Long userId) {
        return userRepository.findById(userId)
                .map(reviewRepository::findAllByUser)
                .orElseThrow(() -> new RuntimeException(String.format("Could not find user with the id: %s", userId)));
    }

    public AttractionRepository getAttractionRepository() {
        return attractionRepository;
    }

    public TourRepository getTourRepository() {
        return tourRepository;
    }

    public ReviewRepository getReviewRepository() {
        return reviewRepository;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }
}
