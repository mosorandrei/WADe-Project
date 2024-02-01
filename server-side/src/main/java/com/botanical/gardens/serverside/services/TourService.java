package com.botanical.gardens.serverside.services;

import com.botanical.gardens.serverside.entities.*;

import java.util.List;

public interface TourService {
    Tour saveTour(Tour tour);
    Tour findTourById(Long tourId);
    List<Tour> fetchTours();
    List<Tour> fetchToursByBotanicalGarden(Long botanicalGardenId);
    void addTourComment(Long tourId, Comment comment);
    void addTourReview(Long tourId, Review review);
    int getNumberOfSeatsForTour(Long tourId);
    void addTourParticipant(Long tourId, User user);
    void deleteTour(Long tourId);
}
