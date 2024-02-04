package com.botanical.gardens.serverside.services;

import com.botanical.gardens.serverside.dto.CommentDTO;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;

public interface CommentService {
    void saveComment(CommentDTO comment) throws OWLOntologyCreationException, OWLOntologyStorageException;
}
