package com.botanical.gardens.serverside.services;

import com.botanical.gardens.serverside.entities.Comment;

import java.util.List;

public interface CommentService {
    Comment saveComment(Comment comment);
    List<Comment> fetchCommentsByAttraction(Long attractionId);
    List<Comment> fetchCommentsByTour(Long tourId);
    List<Comment> fetchCommentsByUser(Long userId);
}
