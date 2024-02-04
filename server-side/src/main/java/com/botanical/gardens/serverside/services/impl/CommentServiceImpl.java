package com.botanical.gardens.serverside.services.impl;

import com.botanical.gardens.serverside.entities.Comment;
import com.botanical.gardens.serverside.repositories.jpa.CommentRepository;
import com.botanical.gardens.serverside.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Comment saveComment(Comment comment) {
        return commentRepository.save(comment);
    }
}
