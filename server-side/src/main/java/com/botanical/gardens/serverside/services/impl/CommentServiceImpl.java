package com.botanical.gardens.serverside.services.impl;

import com.botanical.gardens.serverside.entities.Comment;
import com.botanical.gardens.serverside.services.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;

// TODO: Implement features
@Service
public class CommentServiceImpl implements CommentService {
    @Override
    public void addCommentToAttraction(Long attractionId, Comment comment) {

    }

    @Override
    public void addCommentToTour(Long tourId, Comment comment) {

    }

    @Override
    public List<Comment> fetchCommentsByAttraction(Long attractionId) {
        return null;
    }

    @Override
    public List<Comment> fetchCommentsByTour(Long tourId) {
        return null;
    }

    @Override
    public List<Comment> fetchCommentsByUser(Long userId) {
        return null;
    }
}
