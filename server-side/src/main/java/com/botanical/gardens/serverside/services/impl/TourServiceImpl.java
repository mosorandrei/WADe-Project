package com.botanical.gardens.serverside.services.impl;

import com.botanical.gardens.serverside.entities.*;
import com.botanical.gardens.serverside.repositories.*;
import com.botanical.gardens.serverside.services.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TourServiceImpl implements TourService {

    private final TourRepository tourRepository;

    private final BotanicalGardenRepository botanicalGardenRepository;

    private final CommentRepository commentRepository;

    private final ReviewRepository reviewRepository;

    private final UserRepository userRepository;

    @Autowired
    public TourServiceImpl(TourRepository tourRepository, BotanicalGardenRepository botanicalGardenRepository, CommentRepository commentRepository, ReviewRepository reviewRepository, UserRepository userRepository) {
        this.tourRepository = tourRepository;
        this.botanicalGardenRepository = botanicalGardenRepository;
        this.commentRepository = commentRepository;
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Tour saveTour(Tour tour) {
        return tourRepository.save(tour);
    }

    @Override
    public Tour findTourById(Long tourId) {
        return tourRepository.findById(tourId).orElseThrow(() -> new RuntimeException(String.format("Could not find tour with the id: %s", tourId)));
    }

    @Override
    public List<Tour> fetchTours() {
        return tourRepository.findAll();
    }

    @Override
    public List<Tour> fetchToursByBotanicalGarden(Long botanicalGardenId) {
        return botanicalGardenRepository.findById(botanicalGardenId)
                .map(BotanicalGarden::getTours)
                .orElseThrow(() -> new RuntimeException(String.format("Could not find Botanical Garden with the id: %s", botanicalGardenId)));
    }

    @Override
    public void addTourComment(Long tourId, Comment comment) {
        tourRepository.findById(tourId).ifPresentOrElse(
                tour -> {
                    commentRepository.save(comment);
                    tour.getComments().add(comment);
                    tourRepository.save(tour);
                },
                () -> {
                    throw new RuntimeException(String.format("Could not find tour with the id: %s", tourId));
                });
    }

    @Override
    public void addTourReview(Long tourId, Review review) {
        tourRepository.findById(tourId).ifPresentOrElse(
                tour -> {
                    reviewRepository.save(review);
                    tour.getReviews().add(review);
                    tourRepository.save(tour);
                },
                () -> {
                    throw new RuntimeException(String.format("Could not find tour with the id: %s", tourId));
                });
    }

    @Override
    public int getNumberOfSeatsForTour(Long tourId) {
        return tourRepository.findById(tourId)
                .map(Tour::getTotalSeats)
                .orElseThrow(() -> new RuntimeException(String.format("Could not find tour with the id: %s", tourId)));
    }

    @Override
    public void addTourParticipant(Long tourId, User user) {
        tourRepository.findById(tourId).ifPresentOrElse(
                tour -> {
                    tour.getParticipants().add(user);
                    tourRepository.save(tour);
                },
                () -> {
                    throw new RuntimeException(String.format("Could not find tour with the id: %s", tourId));
                });
    }

    @Override
    public void deleteTour(Long tourId) {
        tourRepository.deleteById(tourId);
    }

    public TourRepository getTourRepository() {
        return tourRepository;
    }

    public BotanicalGardenRepository getBotanicalGardenRepository() {
        return botanicalGardenRepository;
    }

    public CommentRepository getCommentRepository() {
        return commentRepository;
    }

    public ReviewRepository getReviewRepository() {
        return reviewRepository;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }
}
