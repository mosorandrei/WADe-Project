package com.botanical.gardens.serverside.services.impl;

import com.botanical.gardens.serverside.dto.CommentDTO;
import com.botanical.gardens.serverside.repositories.owl.CommentOwlRepository;
import com.botanical.gardens.serverside.services.CommentService;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class CommentServiceImpl implements CommentService {
    private final CommentOwlRepository commentOwlRepository;

    @Autowired
    public CommentServiceImpl(CommentOwlRepository commentOwlRepository) {
        this.commentOwlRepository = commentOwlRepository;
    }

    @Override
    public void saveComment(CommentDTO comment) throws OWLOntologyCreationException, OWLOntologyStorageException {
        commentOwlRepository.addCommentIndividual(comment.getContent(), comment.getTourName(), comment.getFirstName(), comment.getLastName());
    }
}
