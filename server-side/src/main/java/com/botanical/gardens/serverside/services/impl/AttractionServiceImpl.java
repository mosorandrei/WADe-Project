package com.botanical.gardens.serverside.services.impl;

import com.botanical.gardens.serverside.entities.Attraction;
import com.botanical.gardens.serverside.entities.BotanicalGarden;
import com.botanical.gardens.serverside.entities.Comment;
import com.botanical.gardens.serverside.entities.Review;
import com.botanical.gardens.serverside.repositories.AttractionRepository;
import com.botanical.gardens.serverside.repositories.BotanicalGardenRepository;
import com.botanical.gardens.serverside.repositories.CommentRepository;
import com.botanical.gardens.serverside.repositories.ReviewRepository;
import com.botanical.gardens.serverside.services.AttractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttractionServiceImpl implements AttractionService {

    private final AttractionRepository attractionRepository;

    private final CommentRepository commentRepository;

    private final ReviewRepository reviewRepository;

    private final BotanicalGardenRepository botanicalGardenRepository;

    @Autowired
    public AttractionServiceImpl(AttractionRepository attractionRepository, CommentRepository commentRepository, ReviewRepository reviewRepository, BotanicalGardenRepository botanicalGardenRepository) {
        this.attractionRepository = attractionRepository;
        this.commentRepository = commentRepository;
        this.reviewRepository = reviewRepository;
        this.botanicalGardenRepository = botanicalGardenRepository;
    }

    @Override
    public Attraction saveAttraction(Attraction attraction) {
        return attractionRepository.save(attraction);
    }

    @Override
    public Attraction findAttractionById(Long attractionId) {
        return attractionRepository.findById(attractionId).orElseThrow(() -> new RuntimeException(String.format("Could not find attraction with the id: %s", attractionId)));
    }

    @Override
    public List<Attraction> fetchAttractions() {
        return attractionRepository.findAll();
    }

    @Override
    public List<Attraction> fetchAttractionsByBotanicalGarden(Long botanicalGardenId) {
        return botanicalGardenRepository.findById(botanicalGardenId)
                .map(BotanicalGarden::getAttractions)
                .orElseThrow(() -> new RuntimeException(String.format("Could not find Botanical Garden with the id: %s", botanicalGardenId)));
    }

    @Override
    public void addAttractionComment(Long attractionId, Comment comment) {
        attractionRepository.findById(attractionId).ifPresentOrElse(
                attraction -> {
                    commentRepository.save(comment);
                    attraction.getComments().add(comment);
                    attractionRepository.save(attraction);
                },
                () -> {
                    throw new RuntimeException(String.format("Could not find attraction with the id: %s", attractionId));
                });

    }

    @Override
    public void addAttractionReview(Long attractionId, Review review) {
        attractionRepository.findById(attractionId).ifPresentOrElse(
                attraction -> {
                    reviewRepository.save(review);
                    attraction.getReviews().add(review);
                    attractionRepository.save(attraction);
                },
                () -> {
                    throw new RuntimeException(String.format("Could not find attraction with the id: %s", attractionId));
                });

    }

    public AttractionRepository getAttractionRepository() {
        return attractionRepository;
    }

    public CommentRepository getCommentRepository() {
        return commentRepository;
    }

    public ReviewRepository getReviewRepository() {
        return reviewRepository;
    }

    public BotanicalGardenRepository getBotanicalGardenRepository() {
        return botanicalGardenRepository;
    }
}
