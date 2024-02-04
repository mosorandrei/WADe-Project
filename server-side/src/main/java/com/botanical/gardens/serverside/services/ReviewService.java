package com.botanical.gardens.serverside.services;

import com.botanical.gardens.serverside.dto.ReviewDTO;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;

public interface ReviewService {
    void saveReview(ReviewDTO review) throws OWLOntologyCreationException, OWLOntologyStorageException;
}
