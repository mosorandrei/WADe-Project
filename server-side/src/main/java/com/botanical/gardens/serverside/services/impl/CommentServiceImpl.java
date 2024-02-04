package com.botanical.gardens.serverside.services.impl;

import com.botanical.gardens.serverside.entities.Attraction;
import com.botanical.gardens.serverside.entities.Comment;
import com.botanical.gardens.serverside.entities.Tour;
import com.botanical.gardens.serverside.repositories.jpa.AttractionRepository;
import com.botanical.gardens.serverside.repositories.jpa.CommentRepository;
import com.botanical.gardens.serverside.repositories.jpa.TourRepository;
import com.botanical.gardens.serverside.repositories.jpa.UserRepository;
import com.botanical.gardens.serverside.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    private final AttractionRepository attractionRepository;

    private final TourRepository tourRepository;

    private final CommentRepository commentRepository;

    private final UserRepository userRepository;

    @Autowired
    public CommentServiceImpl(AttractionRepository attractionRepository, TourRepository tourRepository, CommentRepository commentRepository, UserRepository userRepository) {
        this.attractionRepository = attractionRepository;
        this.tourRepository = tourRepository;
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Comment saveComment(Comment comment) {
       return commentRepository.save(comment);
    }

    @Override
    public List<Comment> fetchCommentsByAttraction(Long attractionId) {
        return attractionRepository.findById(attractionId)
                .map(Attraction::getComments)
                .orElseThrow(() -> new RuntimeException(String.format("Could not find attraction with the id: %s", attractionId)));
    }

    @Override
    public List<Comment> fetchCommentsByTour(Long tourId) {
        return tourRepository.findById(tourId)
                .map(Tour::getComments)
                .orElseThrow(() -> new RuntimeException(String.format("Could not find tour with the id: %s", tourId)));
    }

    @Override
    public List<Comment> fetchCommentsByUser(Long userId) {
        return userRepository.findById(userId)
                .map(commentRepository::findAllByUser)
                .orElseThrow(() -> new RuntimeException(String.format("Could not find user with the id: %s", userId)));
    }

    public AttractionRepository getAttractionRepository() {
        return attractionRepository;
    }

    public TourRepository getTourRepository() {
        return tourRepository;
    }

    public CommentRepository getCommentRepository() {
        return commentRepository;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }
}
