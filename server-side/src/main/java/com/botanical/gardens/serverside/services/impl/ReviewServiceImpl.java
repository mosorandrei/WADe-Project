package com.botanical.gardens.serverside.services.impl;

import com.botanical.gardens.serverside.entities.Review;
import com.botanical.gardens.serverside.repositories.jpa.ReviewRepository;
import com.botanical.gardens.serverside.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Review saveReview(Review review) {
        return reviewRepository.save(review);
    }
}
