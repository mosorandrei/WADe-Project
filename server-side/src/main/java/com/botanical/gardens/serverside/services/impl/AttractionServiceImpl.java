package com.botanical.gardens.serverside.services.impl;

import com.botanical.gardens.serverside.entities.Attraction;
import com.botanical.gardens.serverside.entities.Comment;
import com.botanical.gardens.serverside.entities.Review;
import com.botanical.gardens.serverside.services.AttractionService;
import org.springframework.stereotype.Service;

import java.util.List;

// TODO: Implement features
@Service
public class AttractionServiceImpl implements AttractionService {
    @Override
    public Attraction saveAttraction(Attraction attraction) {
        return null;
    }

    @Override
    public List<Attraction> fetchAttractions() {
        return null;
    }

    @Override
    public void addAttractionComment(Long attractionId, Comment comment) {

    }

    @Override
    public void addAttractionReview(Long attractionId, Review review) {

    }
}
