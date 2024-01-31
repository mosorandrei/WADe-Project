package com.botanical.gardens.serverside.services;

import com.botanical.gardens.serverside.entities.Attraction;
import com.botanical.gardens.serverside.entities.Comment;
import com.botanical.gardens.serverside.entities.Review;

import java.util.List;

public interface AttractionService {
    Attraction saveAttraction(Attraction attraction);
    List<Attraction> fetchAttractions();
    void addAttractionComment(Long attractionId, Comment comment);
    void addAttractionReview(Long attractionId, Review review);

}
